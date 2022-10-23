package pl.edu.wat.knowledge.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.ArticleRequest;
import pl.edu.wat.knowledge.dto.ArticleResponse;
import pl.edu.wat.knowledge.entity.Article;
import pl.edu.wat.knowledge.service.AuthorService;
import pl.edu.wat.knowledge.service.JournalService;
import pl.edu.wat.knowledge.service.ScoreService;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ArticleMapper extends Mapper<Article, ArticleResponse, ArticleRequest> {

    JournalService journalService;
    AuthorService authorService;
    ScoreService scoreService;

    @Override
    protected void fillReflectionDataInResponse(ArticleResponse response, Article entity) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected void fillReflectionDataInEntity(Article entity, ArticleRequest response) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected Article createEntity(ArticleRequest request) {
        var article = new Article(
                request.getTitle(),
                request.getArticleNo(),
                journalService.getById(request.getJournalId()),
                authorService.getByIds(request.getAuthorIds()),
                request.getCollection(),
                request.getVol(),
                request.getNo(),
                null);

        article.setScore(scoreService.getFor(article));
        return article;
    }

    @Override
    protected ArticleResponse createResponse(Article entity) {
        return new ArticleResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getArticleNo(),
                entity.getJournal().getId(),
                entity.getJournal().getTitle(),
                entity.getAuthors()
                        .stream()
                        .map(it -> "ID:%s %s %s %s".formatted(it.getId(), it.getName(), it.getSurname(), it.getSurname()))
                        .toList(),
                entity.getCollection(),
                entity.getVol(),
                entity.getNo(),
                entity.getScore());
    }
}
