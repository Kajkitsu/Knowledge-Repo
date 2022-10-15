package pl.edu.wat.knowledge.service;

import org.graalvm.polyglot.Context;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScriptServiceTest {
    @Autowired
    ScriptService scriptService;

    @Test
    public void testExecScript() {
        var script = """
                articleRepository.findAll();
                                """;
        var result = scriptService.exec(script);
        assert !result.startsWith("Could not");
    }

}