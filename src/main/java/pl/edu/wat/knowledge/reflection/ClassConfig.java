package pl.edu.wat.knowledge.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassConfig implements Serializable {
    EntityType entityType;
    List<FieldConfig> fieldConfigs;
}
