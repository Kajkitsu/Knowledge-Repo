package pl.edu.wat.knowledge.reflection;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.pool.TypePool;
import org.apache.commons.lang3.tuple.Pair;
import pl.edu.wat.knowledge.exception.ModifyClassException;

import java.util.List;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ClassConfigHandler {
    public static final int SOURCE_ARG_INDEX = 1;
    public static final int TARGET_ARG_INDEX = 0;
    private final ByteBuddy byteBuddy;
    private final List<Pair<String, TypeDescription>> newFieldsForResponse;
    private final List<Pair<String, TypeDescription>> newFieldsForRequest;
    private TypeDescription entity;
    private TypeDescription request;
    private TypeDescription response;
    private TypeDescription mapper;

    public static List<TypeDescription> apply(TypePool typePool, ClassConfig config) {
        var handler = new ClassConfigHandler(typePool, config);
        return List.of(
                handler.entity,
                handler.request,
                handler.response,
                handler.mapper);
    }

    private ClassConfigHandler(TypePool typePool, ClassConfig config) {
        this.entity = typePool.describe(config.getEntityType().getClazzName()).resolve();
        this.response = typePool.describe(config.getEntityType().getClazzName().replace(".entity.", ".dto.") + "Response").resolve();
        this.request = typePool.describe(config.getEntityType().getClazzName().replace(".entity.", ".dto.") + "Request").resolve();
        this.mapper = typePool.describe(config.getEntityType().getClazzName().replace(".entity.", ".mapper.") + "Mapper").resolve();

        List<Pair<String, TypeDescription>> newFieldsForEntity = config.getFieldConfigs()
                .stream()
                .map(it -> Pair.of(it.getName(), toType(typePool, it.getType())))
                .toList();
        this.newFieldsForResponse = config.getFieldConfigs()
                .stream()
                .filter(FieldConfig::getVisibleInResponse)
                .map(it -> Pair.of(it.getName(), toType(typePool, it.getType())))
                .toList();
        this.newFieldsForRequest = config.getFieldConfigs()
                .stream()
                .filter(FieldConfig::getVisibleInRequest)
                .map(it -> Pair.of(it.getName(), toType(typePool, it.getType())))
                .toList();
        this.byteBuddy = new ByteBuddy();

        this.entity = addFields(entity, newFieldsForEntity);
        this.request = addFields(request, newFieldsForRequest);
        this.response = addFields(response, newFieldsForResponse);
        this.mapper = updateMapper();

    }

    private TypeDescription updateMapper() {
        var mapperBuilder = createBuilder(mapper);
        mapperBuilder = mapperBuilder.method(named("fillReflectionDataInEntity"))
                .intercept(FieldMapper.getMappingImpl(
                        entity,
                        request,
                        SOURCE_ARG_INDEX,
                        TARGET_ARG_INDEX,
                        newFieldsForRequest.stream().map(Pair::getLeft).collect(Collectors.toList())));

        mapperBuilder = mapperBuilder.method(named("fillReflectionDataInResponse"))
                .intercept(FieldMapper.getMappingImpl(
                        response,
                        entity,
                        SOURCE_ARG_INDEX,
                        TARGET_ARG_INDEX,
                        newFieldsForResponse.stream().map(Pair::getLeft).toList()));
        return make(mapperBuilder);
    }

    private TypeDescription addFields(TypeDescription entity, List<Pair<String, TypeDescription>> newFieldsForEntity) {
        var entityBuilder = createBuilder(entity);
        entityBuilder = addFields(entityBuilder, newFieldsForEntity);
        entityBuilder = updateToString(entityBuilder);
        return make(entityBuilder);
    }

    private DynamicType.Builder<Object> addFields(DynamicType.Builder<Object> builder, List<Pair<String, TypeDescription>> fields) {
        for (var field : fields) {
            builder = builder
                    .defineProperty(field.getLeft(), field.getRight());
        }
        return builder;
    }

    private TypeDescription make(DynamicType.Builder<Object> builder) {
        var typeDescription = builder.toTypeDescription();
        try (var unloadedObject = builder.make()) {
            unloadedObject
                    .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();
            return typeDescription;

        } catch (Exception e) {
            throw new ModifyClassException(e);
        }
    }

    private DynamicType.Builder<Object> updateToString(DynamicType.Builder<Object> builder) {
        return builder.method(named("toString"))
                .intercept(ToStringMethod.prefixedBySimpleClassName().withTokens("(", ")", ";", "="));
    }

    private DynamicType.Builder<Object> createBuilder(TypeDescription typeDescription) {
        return byteBuddy
                .redefine(typeDescription,
                        ClassFileLocator.ForClassLoader.ofSystemLoader());
    }

    private static TypeDescription toType(TypePool typePool, FieldType type) {
        return typePool.describe(type.getValue()).resolve();
    }

}
