package pl.edu.wat.knowledge;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import pl.edu.wat.classloader.ReflectionClassLoader;

public class MyResourceLoader extends DefaultResourceLoader {
    ClassLoader classLoader;
    public MyResourceLoader(ReflectionClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Resource getResource(String location) {
//        System.out.println("getResource: "+location);
        return super.getResource(location);
    }
    @Override
    public ClassLoader getClassLoader() {
//        System.out.println("getClassLoader: "+cl.getName());
        return this.classLoader;
    }
}
