package pl.edu.wat.knowledge.reflection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.pool.TypePool;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.named;

@Slf4j
public class ReflectionSetter {

    public static void apply(TypePool typePool) throws ModifyClassException {
        var modifiedClasses = ReflectionDefinitionLoader.loadModifiedClass();

        for (var redefinition : modifiedClasses) {
            var entityClassTypeDescription = typePool.describe(redefinition.getEntityType().getClazzName()).resolve();
            var responseClassTypeDescription = typePool.describe(redefinition.getEntityType().getClazzName().replace(".entity.", ".dto.") + "Response").resolve();



            var newFieldsForEntity = redefinition.getAddFieldRequests()
                    .stream()
                    .filter(AddFieldRequest::getVisibleInResponse)
                    .map(it -> Pair.of(it.getName(), toType(typePool, it.getType())))
                    .toList();
            var newFieldsForResponse = redefinition.getAddFieldRequests()
                    .stream()
                    .filter(AddFieldRequest::getVisibleInResponse)
                    .map(it -> Pair.of(it.getName(), toType(typePool, it.getType())))
                    .toList();


            entityClassTypeDescription = redefineClass(entityClassTypeDescription, newFieldsForEntity);
            responseClassTypeDescription = redefineResponseClass(responseClassTypeDescription, entityClassTypeDescription, newFieldsForResponse);
            responseClassTypeDescription.getDeclaredFields();

        }

    }

    private static TypeDescription redefineClass(TypeDescription entityClassTypeDescription, List<Pair<String, TypeDescription>> newFieldsForEntity) {

        var builder =
                addFieldsToClass(
                        newFieldsForEntity,
                        entityClassTypeDescription);
        builder = updateToStringMethod(builder);
        return build(builder);
    }

    private static TypeDescription build(DynamicType.Builder<Object> builder) {

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

    private static TypeDescription redefineResponseClass(TypeDescription responseClassTypeDescription, TypeDescription entityClassTypeDescription, List<Pair<String, TypeDescription>> newFieldsForResponse) {


        var builder = addFieldsToClass(newFieldsForResponse, responseClassTypeDescription);
        builder = updateToStringMethod(builder);


        builder = updateFromMethod(builder, newFieldsForResponse, builder.toTypeDescription(), entityClassTypeDescription);

        return build(builder);

    }

    @SneakyThrows
    private static DynamicType.Builder<Object> updateFromMethod(
            DynamicType.Builder<Object> builder,
            List<Pair<String, TypeDescription>> newFields,
            TypeDescription responseClassTypeDescription,
            TypeDescription entityClassTypeDescription) {
        var oldFromMethod = responseClassTypeDescription
                .getDeclaredMethods()
                .filter(named("from"))
                .get(0);
        var setRankFromMethod = responseClassTypeDescription
                .getDeclaredMethods()
                .filter(named("setRank"))
                .get(0);
        var getRankFromMethod = entityClassTypeDescription
                .getDeclaredMethods()
                .filter(named("getRank"))
                .get(0);
        var rankField = responseClassTypeDescription
                .getDeclaredFields()
                .filter(named("rank"))
                .get(0);

        builder = builder.method(named("fillReflectionData"))
                .intercept(MethodCall.invoke(getRankFromMethod)
                        .onArgument(0)
                        .setsField(rankField));

        return builder;
    }

    private static DynamicType.Builder<Object> updateToStringMethod(DynamicType.Builder<Object> builder) {
        builder = builder.method(named("toString"))
                .intercept(ToStringMethod.prefixedBySimpleClassName());
        return builder;

    }

    private static DynamicType.Builder<Object> addFieldsToClass(List<Pair<String, TypeDescription>> fields, TypeDescription targetClass) {
        var builder = new ByteBuddy()
                .redefine(targetClass,
                        ClassFileLocator.ForClassLoader.ofSystemLoader());

        for (var field : fields) {
            builder = builder
                    .defineProperty(field.getLeft(), field.getRight());
        }
        return builder;

    }

//    private static Class<?> redefineResponseClass(ModifiedClass modifiedClass, TypePool typePool, Class<?> newClass) {
//        try {
//            var newObjectClassType = typePool
//                    .describe(modifiedClass.getEntityType().getClazzName())
//                    .resolve();
//            var responseClassType = typePool
//                    .describe(modifiedClass.getEntityType().getClazzName() + "Response")
//                    .resolve();
//            var builder = new ByteBuddy()
//                    .redefine(responseClassType,
//                            ClassFileLocator.ForClassLoader.ofSystemLoader());
//            Implementation newFromImplementation = newFromImplementation(modifiedClass, responseClassType, typePool
//                    .describe(modifiedClass.getEntityType().getClazzName())
//                    .resolve());
//            var oldFromValue = responseClassType.getDeclaredMethods()
//                    .stream()
//                    .filter(it -> it.getName().equals("from"))
//                    .findFirst().orElseThrow();
//
//            builder.method(named("from"))
//                    .intercept(
//                            MethodCall.invoke(oldFromValue)
//                                    .withArgument(0)
//                                    .andThen(
//
//                                    )
//                    )
//
//
//            builder.defineMethod("fromValue", newObjectClassType, Visibility.PUBLIC)
//                    .withParameter(newObjectClassType)
//                    .intercept(
//                            MethodCall.invoke(
//                                            responseClassType
//                                                    .getDeclaredMethods()
//                                                    .filter(it -> it.getName().equals("from"))
//                                                    .get(0)
//                                    ).onArgument(0)
//                                    .andThen()
//                    )
//                    .
//                    .intercept(newFromImplementation);
//
//            try (var unloadedObject = builder.make()) {
//                return unloadedObject
//                        .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
//                        .getLoaded();
//            } catch (Exception e) {
//                throw new ModifyClassException(e);
//            }
//
//
//        } catch (TypePool.Resolution.NoSuchTypeException exception) {
//            log.error("Response class not found for: " + modifiedClass.getEntityType().getClazzName());
//            throw new ModifyClassException(exception);
//        } catch (Exception e) {
//            throw new ModifyClassException(e);
//        }
//    }
//
//    private static Class<?> redefineResponseClass(ModifiedClass modifiedClass, TypePool typePool, Class<?> newClass) {
//        try {
//            var newObjectClassType = typePool
//                    .describe(modifiedClass.getEntityType().getClazzName())
//                    .resolve();
//            var responseClassType = typePool
//                    .describe(modifiedClass.getEntityType().getClazzName() + "Response")
//                    .resolve();
//            var builder = new ByteBuddy()
//                    .redefine(responseClassType,
//                            ClassFileLocator.ForClassLoader.ofSystemLoader());
//            Implementation newFromImplementation = newFromImplementation(modifiedClass, responseClassType, typePool
//                    .describe(modifiedClass.getEntityType().getClazzName())
//                    .resolve());
//            var oldFromValue = responseClassType.getDeclaredMethods()
//                    .stream()
//                    .filter(it -> it.getName().equals("from"))
//                    .findFirst().orElseThrow();
//
//            builder.method(named("from"))
//                    .intercept(
//                            MethodCall.invoke(oldFromValue)
//                                    .withArgument(0)
//                                    .andThen(
//
//                                    )
//                    )
//
//
//            builder.defineMethod("fromValue", newObjectClassType, Visibility.PUBLIC)
//                    .withParameter(newObjectClassType)
//                    .intercept(
//                            MethodCall.invoke(
//                                            responseClassType
//                                                    .getDeclaredMethods()
//                                                    .filter(it -> it.getName().equals("from"))
//                                                    .get(0)
//                                    ).onArgument(0)
//                                    .andThen()
//                    )
//                    .
//                    .intercept(newFromImplementation);
//
//            try (var unloadedObject = builder.make()) {
//                return unloadedObject
//                        .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
//                        .getLoaded();
//            } catch (Exception e) {
//                throw new ModifyClassException(e);
//            }
//
//
//        } catch (TypePool.Resolution.NoSuchTypeException exception) {
//            log.error("Response class not found for: " + modifiedClass.getEntityType().getClazzName());
//            throw new ModifyClassException(exception);
//        } catch (Exception e) {
//            throw new ModifyClassException(e);
//        }
//    }


    private static TypeDescription toType(TypePool typePool, FieldType type) {
        return typePool.describe(type.getValue()).resolve();
    }

}
