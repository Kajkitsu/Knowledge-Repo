package pl.edu.wat.knowledge.reflection;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.pool.TypePool;
import pl.edu.wat.knowledge.exception.ModifyClassException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReflectionSetter {

    public static void apply(TypePool typePool) throws ModifyClassException {
        List<ClassConfig> classConfigList = ReflectionDefinitionLoader.loadModifiedClass();

        for (ClassConfig config : classConfigList) {
            var retVal = ClassConfigHandler.apply(typePool, config);
            log.debug(retVal.stream().map(TypeDescription::getCanonicalName).collect(Collectors.joining(", ")));
        }
    }



}
