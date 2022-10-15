package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.Affiliation;

public interface AffiliationRepository extends MongoRepository<Affiliation, String> {
}
