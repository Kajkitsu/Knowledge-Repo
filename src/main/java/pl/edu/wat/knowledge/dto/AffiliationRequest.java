package pl.edu.wat.knowledge.dto;

import lombok.Value;

@Value
public class AffiliationRequest {
    String name;
    String parentId;
}
