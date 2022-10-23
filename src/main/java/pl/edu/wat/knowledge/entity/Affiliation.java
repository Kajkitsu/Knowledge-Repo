package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@AllArgsConstructor
public class Affiliation extends Entity {
    @NonNull
    @Indexed(unique = true)
    String name;

    @Nullable
    Affiliation parent;

}

