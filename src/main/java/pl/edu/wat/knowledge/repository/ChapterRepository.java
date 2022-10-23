package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Chapter;

import java.util.Set;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
    Set<Chapter> findAllByBook_Id(String bookId);
}
