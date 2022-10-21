package pl.edu.wat.knowledge.reflection;

import lombok.Value;

@Value
public class AddFieldRequest {
    String name;
    FieldType type;
}
