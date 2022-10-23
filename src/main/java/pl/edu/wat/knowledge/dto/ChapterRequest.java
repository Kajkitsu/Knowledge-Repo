package pl.edu.wat.knowledge.dto;

import lombok.Value;

import java.util.List;

@Value
public class ChapterRequest {
    String title;
    String bookId;
    List<String> authorIds;
    String collection;
}
