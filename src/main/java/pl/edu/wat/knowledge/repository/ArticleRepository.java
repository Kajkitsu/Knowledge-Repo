package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
