package pl.edu.wat.knowledge.exception;

public class ModifyClassException extends RuntimeException {
    public ModifyClassException(Exception e) {
        super(e);
    }

    public ModifyClassException(String message) {
        super(message);
    }

}
