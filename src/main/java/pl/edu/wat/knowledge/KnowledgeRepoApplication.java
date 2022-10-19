package pl.edu.wat.knowledge;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.stream.Collectors;

@SpringBootApplication
public class KnowledgeRepoApplication {

    public static void main(String[] args) {
        System.out.println(System.getProperties().entrySet()
                .stream()
                .sorted(Comparator.comparing(it -> it.getKey().toString()))
                .map(
                        it -> it.getKey().toString()+"="+it.getValue().toString()+"\n"
                ).collect(Collectors.joining()));
        System.out.println(Thread.currentThread().getContextClassLoader());
//        SpringApplication.run(KnowledgeRepoApplication.class, args);
    }

}
