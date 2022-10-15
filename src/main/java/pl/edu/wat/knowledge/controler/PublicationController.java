package pl.edu.wat.knowledge.controler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.knowledge.dto.PublicationResponse;
import pl.edu.wat.knowledge.service.PublicationService;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/api/publication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PublicationController {

    PublicationService publicationService;

    @GetMapping
    public ResponseEntity<Collection<PublicationResponse>> getAll() {
        return new ResponseEntity<>(publicationService.getAll(), HttpStatus.ACCEPTED);
    }

}
