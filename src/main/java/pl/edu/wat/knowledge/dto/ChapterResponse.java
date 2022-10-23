package pl.edu.wat.knowledge.dto;

import lombok.Value;

import java.util.List;

@Value
public class ChapterResponse {
    String id;
    String title;
    String bookId;
    String bookName;
    List<String> authors;
    String collection;
}
