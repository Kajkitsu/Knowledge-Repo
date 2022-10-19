package pl.edu.wat.classloader;

public class ModifyClassException extends RuntimeException {
    public ModifyClassException(Exception e) {
      super(e);
    }
}
