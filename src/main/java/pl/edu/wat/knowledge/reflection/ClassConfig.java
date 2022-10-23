package pl.edu.wat.knowledge.reflection;

import lombok.Value;

import java.util.List;

@Value
public class ClassConfig {
    EntityType entityType;
    List<FieldConfig> fieldConfigs;
}
