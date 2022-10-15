package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Entity {
    @NonNull
    private final Instant createDate = Instant.now();
    @Id
    private String id;
}