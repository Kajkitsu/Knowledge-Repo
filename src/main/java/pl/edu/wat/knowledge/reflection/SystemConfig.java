package pl.edu.wat.knowledge.reflection;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SystemConfig implements Serializable {
    List<ClassConfig> clasConfigs;
}
