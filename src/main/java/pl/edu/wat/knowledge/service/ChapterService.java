package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.entity.Book;
import pl.edu.wat.knowledge.entity.Chapter;
import pl.edu.wat.knowledge.repository.ChapterRepository;

import java.util.Set;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChapterService {
    ChapterRepository chapterRepository;

    public Set<Chapter> getAllOf(Book book) {
        return chapterRepository.findAllByBook_Id(book.getId());
    }
}
