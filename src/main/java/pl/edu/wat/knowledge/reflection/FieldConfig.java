package pl.edu.wat.knowledge.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldConfig implements Serializable {
    String name;
    FieldType type;
    Boolean visibleInResponse;
    Boolean visibleInRequest;
}
