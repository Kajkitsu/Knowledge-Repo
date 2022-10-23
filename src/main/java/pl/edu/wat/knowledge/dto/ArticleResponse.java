package pl.edu.wat.knowledge.dto;

import lombok.Value;

import java.util.List;

@Value
public class ArticleResponse {
    String id;
    String title;
    Integer articleNo;
    String journalId;
    String journalName;
    List<String> authors;
    String collection;
    Integer vol;
    Integer no;
    Integer score;
}
