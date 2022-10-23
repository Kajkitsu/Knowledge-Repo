package pl.edu.wat.knowledge;

import net.bytebuddy.pool.TypePool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.knowledge.exception.ModifyClassException;
import pl.edu.wat.knowledge.reflection.ReflectionSetter;

@SpringBootApplication
public class KnowledgeRepoApplication {

    public static void main(String[] args) throws ModifyClassException {

        ReflectionSetter.apply(TypePool.Default.ofSystemLoader());

        SpringApplication.run(KnowledgeRepoApplication.class, args);
    }



}
