package pl.edu.wat.knowledge.dto;

import lombok.Value;

@Value
public class AuthorRequest {
    String name;
    String surname;
    String affiliationId;
}
