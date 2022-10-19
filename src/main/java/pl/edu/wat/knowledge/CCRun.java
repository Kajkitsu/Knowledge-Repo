package pl.edu.wat.knowledge;

import pl.edu.wat.classloader.ReflectionClassLoader;
import pl.edu.wat.classloader.ReflectionEntityLoader;

import java.lang.reflect.Method;

public class CCRun {


    public static void main(String args[]) throws Exception {
        String progClass = "pl.edu.wat.knowledge.KnowledgeRepoApplication";

        ReflectionClassLoader ccl = new ReflectionClassLoader(CCRun.class.getClassLoader());

        ReflectionEntityLoader entityLoader = new ReflectionEntityLoader();


        Class clas = ccl.loadClass(progClass);
        Class mainArgType[] = { (new String[0]).getClass() };
        Method main = clas.getMethod("main", mainArgType);
        Object argsArray[] = { args };
        main.invoke(null, argsArray);

        // Below method is used to check that the Foo is getting loaded
        // by our custom class loader i.e CCLoader
//        Method printCL = clas.getMethod("printCL", null);
//        printCL.invoke(null, new Object[0]);
    }


}
