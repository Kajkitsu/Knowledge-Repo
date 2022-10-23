package pl.edu.wat.knowledge.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.AffiliationRequest;
import pl.edu.wat.knowledge.dto.AffiliationResponse;
import pl.edu.wat.knowledge.entity.Affiliation;
import pl.edu.wat.knowledge.service.AffiliationService;

import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AffiliationMapper extends Mapper<Affiliation, AffiliationResponse, AffiliationRequest> {

    AffiliationService affiliationService;

    @Override
    protected void fillReflectionDataInResponse(AffiliationResponse response, Affiliation entity) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected void fillReflectionDataInEntity(Affiliation entity, AffiliationRequest response) {
        //intentionally blank, reflection setter fill that field on init
    }

    @Override
    protected Affiliation createEntity(AffiliationRequest request) {
        return new Affiliation(
                request.getName(),
                Optional.ofNullable(request.getParentId()).map(affiliationService::getById).orElse(null));
    }

    @Override
    protected AffiliationResponse createResponse(Affiliation entity) {
        return new AffiliationResponse(
                entity.getId(),
                entity.getName(),
                Optional.ofNullable(entity.getParent()).map(Affiliation::getId).orElse(null),
                Optional.ofNullable(entity.getParent()).map(Affiliation::getName).orElse(null)
        );
    }
}
