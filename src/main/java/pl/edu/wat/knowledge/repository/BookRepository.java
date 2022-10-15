package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
