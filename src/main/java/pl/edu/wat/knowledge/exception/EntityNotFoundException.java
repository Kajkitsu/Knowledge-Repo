package pl.edu.wat.knowledge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.wat.knowledge.entity.Entity;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public EntityNotFoundException(Class<? extends Entity> entityClass, String id) {
        this("%s not found for given id:%s".formatted(entityClass.getSimpleName(), id));
    }
}

