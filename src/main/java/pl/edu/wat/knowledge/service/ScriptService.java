package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.repository.AffiliationRepository;
import pl.edu.wat.knowledge.repository.ArticleRepository;
import pl.edu.wat.knowledge.repository.AuthorRepository;
import pl.edu.wat.knowledge.repository.BookRepository;
import pl.edu.wat.knowledge.repository.ChapterRepository;
import pl.edu.wat.knowledge.repository.JournalSeriesRepository;
import pl.edu.wat.knowledge.repository.PublisherRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class ScriptService {
    AffiliationRepository affiliationRepository;
    ArticleRepository articleRepository;
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    ChapterRepository chapterRepository;
    JournalSeriesRepository journalSeriesRepository;
    PublisherRepository publisherRepository;

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("affiliationRepository", affiliationRepository);
            bindings.putMember("articleRepository", articleRepository);
            bindings.putMember("authorRepository", authorRepository);
            bindings.putMember("bookRepository", bookRepository);
            bindings.putMember("chapterRepository", chapterRepository);
            bindings.putMember("journalSeriesRepository", journalSeriesRepository);
            bindings.putMember("publisherRepository", publisherRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }

    }

}