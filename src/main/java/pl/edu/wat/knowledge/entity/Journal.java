package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Builder
public class Journal extends Entity {

    @NonNull
    Publisher publisher;

    @NonNull
    String title;

    @NonNull
    Issn issn;
    @NonNull
    Integer baseScore;

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @Getter
    public static class Issn {
        private static final Pattern ISSN_PATTERN = Pattern.compile("\\d[\\d\\-]+\\d");
        String value;

        public Issn(String value) {
            assert ISSN_PATTERN.matcher(value).matches();
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
