package pl.edu.wat.knowledge;

import javassist.ClassPool;
import javassist.CtField;
import javassist.CtNewMethod;
import org.junit.jupiter.api.Test;
import pl.edu.wat.knowledge.entity.Affiliation;
import pl.edu.wat.knowledge.entity.Entity;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class JavaassistTest {

    @Test
    void addFieldToAuthor() {
        var newAuthorWithRank = getAuthorWithExtraField();
        assert "Author(rank=sierż. pchor., name=Jan, surname=Kowalski, affiliation=null)".equals(newAuthorWithRank.toString());
    }

    Entity getAuthorWithExtraField() {
        try {
            var classPool = ClassPool.getDefault();
            var ctClass = classPool.get("pl.edu.wat.knowledge.entity.Author");
            var newField = new CtField(classPool.get("java.lang.String"), "rank", ctClass);
            newField.setModifiers(Modifier.PRIVATE);
            ctClass.addMethod(CtNewMethod.getter("getRank", newField));
            ctClass.addMethod(CtNewMethod.setter("setRank", newField));
            ctClass.addField(newField);
            var toStringMethod = ctClass.getDeclaredMethod("toString");
            toStringMethod.setBody(
                    """
            return "Author(rank="+this.getRank()
                +", name=" + this.getName() +
                ", surname=" + this.getSurname() 
                + ", affiliation=" + this.getAffiliation() + ")";
                            """);
            Class authorClass = ctClass.toClass(Entity.class);
            Entity newAuthorEntity = (Entity)authorClass.getConstructor(String.class, String.class, Affiliation.class)
                    .newInstance("Jan","Kowalski",null);
            Method method = authorClass.getMethod("setRank", String.class);
            method.invoke(newAuthorEntity, "sierż. pchor.");
            return newAuthorEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
