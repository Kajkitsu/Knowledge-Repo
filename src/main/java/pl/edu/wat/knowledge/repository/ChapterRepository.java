package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Chapter;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
}
