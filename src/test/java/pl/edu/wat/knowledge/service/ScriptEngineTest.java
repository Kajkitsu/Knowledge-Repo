package pl.edu.wat.knowledge.service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wat.knowledge.repository.AuthorRepository;

@SpringBootTest
class ScriptEngineTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testExecScript() {
        //given
        var script = """
                var authors = authorRepository.findAll();
                authors.stream()
                    .map(function (it) { return it.getName()+" "+it.getSurname() })
                    .toList();
                                """;
        var expected = "[Marcin Krukowski, Piotr Jawowski, Patrycja Woda]";
        //then
        var result = execScript(script);

        //expect
        assert result.equals(expected);
    }

    public String execScript(String script) {
        String result = null;
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("authorRepository", authorRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }

}


