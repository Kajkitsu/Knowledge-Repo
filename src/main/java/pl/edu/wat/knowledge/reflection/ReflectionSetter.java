package pl.edu.wat.knowledge.reflection;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.pool.TypePool;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ReflectionSetter {

    public static void apply(TypePool typePool) throws ModifyClassException {
        var modifiedClasses = ReflectionDefinitionLoader.loadModifiedClass();

        for(var redefinition: modifiedClasses) {
            redefineClass(redefinition, typePool);
        }

    }

    private static Class<?> redefineClass(ModifiedClass redefinition, TypePool typePool) {

        var objectTypeDescription = typePool.describe(redefinition.getEntityType().getClazzName()).resolve();
        var builder = new ByteBuddy()
                .redefine(objectTypeDescription,
                        ClassFileLocator.ForClassLoader.ofSystemLoader());

        for (var field: redefinition.getAddFieldRequests()) {
            builder = builder
                    .defineProperty(field.getName(), toType(typePool, field.getType()))
                    .method(named("toString")).intercept(ToStringMethod.prefixedBySimpleClassName());
        }

        try(var unloadedObject = builder.make()){
            return unloadedObject
                    .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();
        } catch (Exception e){
            throw  new ModifyClassException(e);
        }

    }

    private static TypeDescription toType(TypePool typePool, FieldType type) {
        return typePool.describe(type.getValue()).resolve();
    }

}
