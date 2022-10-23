package pl.edu.wat.knowledge.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.ChapterRequest;
import pl.edu.wat.knowledge.dto.ChapterResponse;
import pl.edu.wat.knowledge.entity.Chapter;
import pl.edu.wat.knowledge.service.AuthorService;
import pl.edu.wat.knowledge.service.BookService;
import pl.edu.wat.knowledge.service.ScoreService;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChapterMapper extends Mapper<Chapter, ChapterResponse, ChapterRequest> {

    BookService bookService;
    AuthorService authorService;
    ScoreService scoreService;

    @Override
    protected void fillReflectionDataInResponse(ChapterResponse response, Chapter entity) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected void fillReflectionDataInEntity(Chapter entity, ChapterRequest response) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected Chapter createEntity(ChapterRequest request) {
        var chapter = new Chapter(
                request.getTitle(),
                bookService.getById(request.getBookId()),
                authorService.getByIds(request.getAuthorIds()),
                request.getCollection(),
                null);

        chapter.setScore(scoreService.getFor(chapter));
        return chapter;
    }

    @Override
    protected ChapterResponse createResponse(Chapter entity) {
        return new ChapterResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getBook().getId(),
                entity.getBook().getTitle(),
                entity.getAuthors()
                        .stream()
                        .map(it -> "ID:%s %s %s %s".formatted(it.getId(), it.getName(), it.getSurname(), it.getSurname()))
                        .toList(),
                entity.getCollection()
        );
    }
}
