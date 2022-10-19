package pl.edu.wat.classloader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityType {
    AFFILIATION("pl.edu.wat.knowledge.entity.Affiliation"),
    AUTHOR("pl.edu.wat.knowledge.entity.Author"),
    ARTICLE("pl.edu.wat.knowledge.entity.Article"),
    CHAPTER("pl.edu.wat.knowledge.entity.Chapter");

    private final String clazzName;
}
