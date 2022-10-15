package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
