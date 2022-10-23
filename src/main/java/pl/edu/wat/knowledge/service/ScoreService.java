package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.entity.Article;
import pl.edu.wat.knowledge.entity.Chapter;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScoreService {

    ChapterService chapterService;

    public Integer getFor(Chapter chapter) {
        Set<Chapter> bookList = chapterService.getAllOf(chapter.getBook());
        return chapter.getBook().getBaseScore() / bookList.size();
    }

    public Integer getFor(Article article) {
        return article.getJournal().getBaseScore();
    }
}
