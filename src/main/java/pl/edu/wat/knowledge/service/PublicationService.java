package pl.edu.wat.knowledge.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.knowledge.dto.PublicationResponse;
import pl.edu.wat.knowledge.repository.ArticleRepository;
import pl.edu.wat.knowledge.repository.ChapterRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PublicationService {

    ArticleRepository articleRepository;
    ChapterRepository chapterRepository;

    public Collection<PublicationResponse> getAll() {
        return Stream.concat(
                        articleRepository.findAll().stream().map(PublicationResponse::from),
                        chapterRepository.findAll().stream().map(PublicationResponse::from))
                .collect(Collectors.toList());

    }
}
