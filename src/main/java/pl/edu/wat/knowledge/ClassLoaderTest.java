package pl.edu.wat.knowledge;



public class ClassLoaderTest {

    public static void main(String[] args) {

        System.out.println("class loader for HashMap: "
                + java.util.HashMap.class.getClassLoader());
        System.out.println("class loader for SpringApplication: "
                + org.springframework.boot.SpringApplication.class
                .getClassLoader());
        System.out.println("class loader for this class: "
                + ClassLoaderTest.class.getClassLoader());
        System.out.println("class loader for MongoRepository: "
                        + org.springframework.data.mongodb.repository.MongoRepository.class.getClassLoader());

    }

}