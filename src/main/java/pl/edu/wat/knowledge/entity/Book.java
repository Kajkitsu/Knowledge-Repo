package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Builder
public class Book extends Entity {
    @Indexed(unique = true)
    @NonNull
    String title;

    @NonNull
    Publisher publisher;

    @NonNull
    Integer year;

    @NonNull
    Isbn isbn;

    @NonNull
    Integer baseScore;

    @Nullable
    Author editor;


    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @Getter
    public static class Isbn {
        private static final Pattern ISBN_PATTERN = Pattern.compile("\\d[\\d\\-]+\\d");
        String value;

        public Isbn(String value) {
            assert ISBN_PATTERN.matcher(value).matches();
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
