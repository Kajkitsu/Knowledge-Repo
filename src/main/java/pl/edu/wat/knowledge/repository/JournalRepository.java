package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Journal;

public interface JournalRepository extends MongoRepository<Journal, String> {
}
