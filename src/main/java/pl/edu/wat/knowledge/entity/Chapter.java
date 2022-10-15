package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Builder
public class Chapter extends Entity {

    @NonNull
    String title;

    @NonNull
    Book book;

    @NonNull
    Set<Author> authors;

    @NonNull
    String collection;

    @NonNull
    Integer score;

}
