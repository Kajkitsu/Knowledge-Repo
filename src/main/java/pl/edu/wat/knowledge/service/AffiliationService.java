package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.entity.Affiliation;
import pl.edu.wat.knowledge.exception.EntityNotFoundException;
import pl.edu.wat.knowledge.repository.AffiliationRepository;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AffiliationService {
    AffiliationRepository affiliationRepository;
    public Affiliation getById(String id) {
        return affiliationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Affiliation.class, id));
    }
}
