package pl.edu.wat.classloader;

public class ReflectionClassLoader extends ClassLoader {

    private final ReflectionEntityLoader entityLoader;

    public ReflectionClassLoader(ClassLoader contextClassLoader) {
        super(contextClassLoader); //TODO maybe parent is not need
        Thread.currentThread().setContextClassLoader(this);
        entityLoader = new ReflectionEntityLoader();
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("Loading class: " + name);
        if (entityLoader.getClassNames().contains(name)) {
            System.out.println("Loading custom class: " + name);
            return entityLoader.findClass(name);
        }
        return super.loadClass(name, resolve);
    }

}