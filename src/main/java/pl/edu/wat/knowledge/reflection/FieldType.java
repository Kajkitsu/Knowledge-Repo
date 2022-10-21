package pl.edu.wat.knowledge.reflection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public enum FieldType {
    STRING("java.lang.String"),
    FLOAT("java.lang.Float"),
    DOUBLE("java.lang.Double"),
    INT("java.lang.Integer"),
    LONG("java.lang.Long"),
    BOOLEAN("java.lang.Boolean");

    String value;
}
