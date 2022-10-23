package pl.edu.wat.knowledge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.knowledge.entity.Author;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorResponse {

    String id;
    String name;
    String surname;
    String affiliationId;
    String affiliationName;

    public static AuthorResponse from(Author author) {
        var response = new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getAffiliation().getId(),
                author.getAffiliation().getName()
        );
        response.fillReflectionData(author);
        return response;
    }

    private void fillReflectionData(Author author) {
        //intentionally blank, reflection setter fill that field on init
    }

}
