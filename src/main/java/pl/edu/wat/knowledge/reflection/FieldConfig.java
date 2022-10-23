package pl.edu.wat.knowledge.reflection;

import lombok.Value;

@Value
public class FieldConfig {
    String name;
    FieldType type;
    Boolean visibleInResponse;
    Boolean visibleInRequest;
}
