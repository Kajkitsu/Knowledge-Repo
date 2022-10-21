package pl.edu.wat.knowledge.reflection;

import lombok.Value;

import java.util.List;

@Value
public class ModifiedClass {
    EntityType entityType;
    List<AddFieldRequest> addFieldRequests;
}
