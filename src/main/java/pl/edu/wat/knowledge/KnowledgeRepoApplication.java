package pl.edu.wat.knowledge;

import net.bytebuddy.pool.TypePool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.knowledge.exception.ModifyClassException;
import pl.edu.wat.knowledge.reflection.ClassConfig;
import pl.edu.wat.knowledge.reflection.ReflectionDefinitionLoader;
import pl.edu.wat.knowledge.reflection.ReflectionSetter;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class KnowledgeRepoApplication {

    public static void main(String[] args) throws ModifyClassException {

        List<ClassConfig> classConfigList = ReflectionDefinitionLoader.loadModifiedClass(
                new File("./reflection_config.json")
        );

        ReflectionSetter.apply(TypePool.Default.ofSystemLoader(), classConfigList);

        SpringApplication.run(KnowledgeRepoApplication.class, args);
    }


}
