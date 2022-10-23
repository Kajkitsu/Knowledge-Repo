package pl.edu.wat.knowledge.reflection;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.wat.knowledge.exception.ModifyClassException;

import java.io.File;
import java.util.List;

public class ReflectionDefinitionLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<ClassConfig> loadModifiedClass(File file) {
        try {
            return objectMapper.readValue(file, SystemConfig.class)
                    .getClasConfigs();
        } catch (Exception e) {
            throw new ModifyClassException(e);
        }
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
