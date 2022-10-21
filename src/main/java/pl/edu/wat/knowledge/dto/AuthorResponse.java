package pl.edu.wat.knowledge.dto;

import lombok.Value;

@Value
public class AuthorResponse {

    String id;
    String name;
    String surname;
    String affiliationName;
    String affiliationId;
}
