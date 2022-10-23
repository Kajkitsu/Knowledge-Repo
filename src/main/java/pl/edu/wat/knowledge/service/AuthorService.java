package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.AuthorResponse;
import pl.edu.wat.knowledge.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorService {
    AuthorRepository authorRepository;


    public List<AuthorResponse> getAll() { //TODO change return Type to AuthorResponse
        return authorRepository.findAll()
                .stream()
                .map(AuthorResponse::from)
                .collect(Collectors.toList());
    }
}
