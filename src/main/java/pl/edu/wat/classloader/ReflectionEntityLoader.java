package pl.edu.wat.classloader;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.edu.wat.knowledge.entity.Entity;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReflectionEntityLoader {

    ClassPool classPool;
    Map<String, ModifiedClass> modifiedClasses;
    @Getter
    Set<String> classNames;

    public ReflectionEntityLoader() {
        classPool = ClassPool.getDefault();
        modifiedClasses = loadModifiedClass();
        classNames = new HashSet<>(modifiedClasses.keySet());
    }

    private Map<String, ModifiedClass> loadModifiedClass() { //TODO
        return Map.of(
                EntityType.AUTHOR.getClazzName(),
                new ModifiedClass(
                        EntityType.AUTHOR,
                        List.of(
                                new AddFieldRequest("rank", FieldType.STRING)
                        )
                )
        );
    }


    public Class<?> findClass(String name) {
        try {
            var modifiedClass = modifiedClasses.get(name);
            var ctClass = classPool.get(name);
            for (var newFieldProperties : modifiedClass.getAddFieldRequests()) {
                var newField = new CtField(toClass(newFieldProperties.getType()), newFieldProperties.getName(), ctClass);
                newField.setModifiers(Modifier.PRIVATE);
                ctClass.addField(newField);
                ctClass.addMethod(CtNewMethod.getter("get" + newFieldProperties.getName().substring(0, 1).toUpperCase() + newFieldProperties.getName().substring(1), newField));
                ctClass.addMethod(CtNewMethod.getter("set" + newFieldProperties.getName().substring(0, 1).toUpperCase() + newFieldProperties.getName().substring(1), newField));
                var toStringMethod = ctClass.getDeclaredMethod("toString");
                toStringMethod.setBody(getToStringBody(ctClass));
            }
            return ctClass.toClass(Entity.class);


        } catch (Exception e) {
            throw new ModifyClassException(e);
        }

    }

    public CtClass createClass(ModifiedClass modifiedClass) {
        try {
            var ctClass = classPool.get(modifiedClass.getEntityType().getClazzName());
            for (var newFieldProperties : modifiedClass.getAddFieldRequests()) {
                var newField = new CtField(toClass(newFieldProperties.getType()), newFieldProperties.getName(), ctClass);
                newField.setModifiers(Modifier.PRIVATE);
                ctClass.addField(newField);
                ctClass.addMethod(CtNewMethod.getter("get" + newFieldProperties.getName().substring(0, 1).toUpperCase() + newFieldProperties.getName().substring(1), newField));
                ctClass.addMethod(CtNewMethod.getter("set" + newFieldProperties.getName().substring(0, 1).toUpperCase() + newFieldProperties.getName().substring(1), newField));
                var toStringMethod = ctClass.getDeclaredMethod("toString");
                toStringMethod.setBody(getToStringBody(ctClass));
            }
            return ctClass;


        } catch (Exception e) {
            throw new ModifyClassException(e);
        }

    }

    private String getToStringBody(CtClass ctClass) { //TODO
        return """
                return "DU*A";
                """;
    }

    private CtClass toClass(FieldType type) throws NotFoundException {
        return classPool.get(type.getValue());
    }

    public List<CtClass> getCtClasses() {
        return modifiedClasses
                .values()
                .stream()
                .map(this::createClass)
                .toList();
    }
}
