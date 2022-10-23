package pl.edu.wat.knowledge.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.AuthorRequest;
import pl.edu.wat.knowledge.dto.AuthorResponse;
import pl.edu.wat.knowledge.entity.Author;
import pl.edu.wat.knowledge.service.AffiliationService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorMapper extends Mapper<Author, AuthorResponse, AuthorRequest> {
    AffiliationService affiliationService;


    @Override
    protected void fillReflectionDataInEntity(Author entity, AuthorRequest request) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected Author createEntity(AuthorRequest request) {
        return Author.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .affiliation(affiliationService.getById(request.getAffiliationId()))
                .build();
    }


    @Override
    protected void fillReflectionDataInResponse(AuthorResponse response, Author entity) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected AuthorResponse createResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getAffiliation().getId(),
                author.getAffiliation().getName()
        );
    }
}
