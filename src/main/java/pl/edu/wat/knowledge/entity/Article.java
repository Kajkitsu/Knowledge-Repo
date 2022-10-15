package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
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
public class Article extends Entity {

    @NonNull
    String title;

    @Nullable
    Integer articleNo;

    @NonNull
    Journal journal;

    @NonNull
    Set<Author> authors;

    @NonNull
    String collection;

    @NonNull
    Integer vol;

    @Nullable
    Integer no;

    @NonNull
    Integer score;

}
