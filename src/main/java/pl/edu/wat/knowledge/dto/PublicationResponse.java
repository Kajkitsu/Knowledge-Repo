package pl.edu.wat.knowledge.dto;

import lombok.Value;
import pl.edu.wat.knowledge.entity.Article;
import pl.edu.wat.knowledge.entity.Chapter;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
public class PublicationResponse {

    String id;
    String type;
    String title;
    String authors;
    Integer score;
    String collection;
    String seriesTitle;
    String issn;
    String publisher;
    String extra;


    public static PublicationResponse from(Article article) {
        return new PublicationResponse(
                article.getId(),
                "ARTICLE",
                article.getTitle(),
                article.getAuthors().stream()
                        .map(it -> "%s %s".formatted(it.getName(), it.getSurname()))
                        .collect(Collectors.joining(", ")),
                article.getScore(),
                article.getCollection(),
                article.getJournal().getTitle(),
                article.getJournal().getIssn().toString(),
                article.getJournal().getPublisher().getName() + ":" + article.getJournal().getPublisher().getLocation(),
                Stream.of(
                                Optional.ofNullable(article.getVol()).map(it -> "vol." + it),
                                Optional.ofNullable(article.getNo()).map(it -> "no." + it),
                                Optional.ofNullable(article.getArticleNo()).map(it -> "art. no." + it)
                        ).flatMap(Optional::stream)
                        .collect(Collectors.joining(", ")));

    }

    public static PublicationResponse from(Chapter chapter) {
        return new PublicationResponse(
                chapter.getId(),
                "CHAPTER",
                chapter.getTitle(),
                chapter.getAuthors().stream()
                        .map(it -> "%s %s".formatted(it.getName(), it.getSurname()))
                        .collect(Collectors.joining(", ")),
                chapter.getScore(),
                chapter.getCollection(),
                chapter.getBook().getTitle(),
                chapter.getBook().getIsbn().toString(),
                chapter.getBook().getPublisher().getName() + ":" + chapter.getBook().getPublisher().getLocation(),
                "");

    }
}
