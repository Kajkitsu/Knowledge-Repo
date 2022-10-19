package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Builder
public class Author extends Entity {
    @NonNull
    String name;

    @NonNull
    String surname;

    @NonNull
    Affiliation affiliation;

}