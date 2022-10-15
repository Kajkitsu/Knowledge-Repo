package pl.edu.wat.knowledge.entity;

import com.mongodb.lang.NonNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Entity {
    @NonNull
    private Instant createDate = Instant.now();
    @Id
    private String id;
}