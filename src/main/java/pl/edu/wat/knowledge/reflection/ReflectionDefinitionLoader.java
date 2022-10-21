package pl.edu.wat.knowledge.reflection;

import java.util.List;

public class ReflectionDefinitionLoader {
    public static List<ModifiedClass> loadModifiedClass() {
        return List.of(
                new ModifiedClass(EntityType.AUTHOR,
                        List.of(
                                new AddFieldRequest("rank",FieldType.STRING)
                        ))
        );
    }
}
