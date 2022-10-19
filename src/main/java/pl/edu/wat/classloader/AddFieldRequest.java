package pl.edu.wat.classloader;

import lombok.Value;

@Value
public class AddFieldRequest {
    String name;
    FieldType type;
}
