package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.entity.Journal;
import pl.edu.wat.knowledge.exception.EntityNotFoundException;
import pl.edu.wat.knowledge.repository.JournalRepository;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JournalService {

    JournalRepository journalRepository;

    public Journal getById(String id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Journal.class, id));
    }
}
