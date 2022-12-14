package pl.edu.wat.knowledge.controler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.knowledge.dto.AuthorRequest;
import pl.edu.wat.knowledge.dto.AuthorResponse;
import pl.edu.wat.knowledge.service.AuthorService;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/api/author")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorController {

    AuthorService authorService;

    @GetMapping
    public ResponseEntity<Collection<AuthorResponse>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<AuthorResponse> save(@RequestBody AuthorRequest request) {
        return new ResponseEntity<>(authorService.save(request), HttpStatus.ACCEPTED);
    }

}
