package pl.edu.wat.knowledge.dto;

import lombok.Value;
import pl.edu.wat.knowledge.entity.Author;

@Value
public class AuthorResponse {

    String id;
    String name;
    String surname;
    String affiliationName;
    String affiliationId;

    public static AuthorResponse from(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getAffiliation().getName(),
                author.getAffiliation().getId()
        );
    }
}
