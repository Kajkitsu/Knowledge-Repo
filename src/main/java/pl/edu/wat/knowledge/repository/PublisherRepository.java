package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Publisher;

public interface PublisherRepository extends MongoRepository<Publisher, String> {
}
