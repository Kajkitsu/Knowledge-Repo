package pl.edu.wat.knowledge.dto;

import lombok.Value;

import java.util.List;

@Value
public class ArticleRequest {
    String title;
    Integer articleNo;
    String journalId;
    List<String> authorIds;
    String collection;
    Integer vol;
    Integer no;
}
