package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.AuthorRequest;
import pl.edu.wat.knowledge.dto.AuthorResponse;
import pl.edu.wat.knowledge.mapper.AuthorMapper;
import pl.edu.wat.knowledge.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;


    public List<AuthorResponse> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public AuthorResponse save(AuthorRequest request) {
        return authorMapper.mapToResponse(
                authorRepository.save(
                        authorMapper.mapToEntity(request)));
    }
}
