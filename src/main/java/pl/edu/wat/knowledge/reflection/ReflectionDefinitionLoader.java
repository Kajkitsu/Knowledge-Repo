package pl.edu.wat.knowledge.reflection;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ReflectionDefinitionLoader {
    public static List<ClassConfig> loadModifiedClass(File file) {
//        new Writa
//////// TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return Collections.emptyList();
    }

    public static List<ClassConfig> loadModifiedClassTest() {
        return List.of(
                new ClassConfig(EntityType.AUTHOR,
                        List.of(
                                new FieldConfig("rank", FieldType.STRING, true, true)
                        )
                )
        );
    }
}
