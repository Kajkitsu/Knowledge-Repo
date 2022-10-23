package pl.edu.wat.knowledge.controler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.knowledge.service.ScriptService;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScriptController {
    ScriptService scriptService;

    @PutMapping
    public ResponseEntity<String> exec(@RequestBody String script) {
        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.ACCEPTED);
    }
}
