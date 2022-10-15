package pl.edu.wat.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.wat.knowledge.entity.JournalSeries;

public interface JournalSeriesRepository extends MongoRepository<JournalSeries, String> {
}
