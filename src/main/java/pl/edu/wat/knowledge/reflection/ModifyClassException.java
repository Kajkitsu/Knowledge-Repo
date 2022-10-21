package pl.edu.wat.knowledge.reflection;

public class ModifyClassException extends RuntimeException {
    public ModifyClassException(Exception e) {
      super(e);
    }
}
