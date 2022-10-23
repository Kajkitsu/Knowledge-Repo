package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.entity.Book;
import pl.edu.wat.knowledge.exception.EntityNotFoundException;
import pl.edu.wat.knowledge.repository.BookRepository;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookService {
    BookRepository bookRepository;

    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Book.class, id));
    }
}
