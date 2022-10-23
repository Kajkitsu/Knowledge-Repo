package pl.edu.wat.knowledge.reflection;

import java.util.List;

public class ReflectionDefinitionLoader {
    public static List<ClassConfig> loadModifiedClass() {
        return List.of(
                new ClassConfig(EntityType.AUTHOR,
                        List.of(
                                new FieldConfig("rank", FieldType.STRING, true, true)
                        )
                )
        );
    }
}
