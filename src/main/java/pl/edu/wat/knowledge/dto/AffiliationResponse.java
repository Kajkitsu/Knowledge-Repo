package pl.edu.wat.knowledge.dto;

import lombok.Value;

@Value
public class AffiliationResponse {
    String id;
    String name;
    String parentId;
    String parentName;
}
