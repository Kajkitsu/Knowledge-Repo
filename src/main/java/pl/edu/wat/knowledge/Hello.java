package pl.edu.wat.knowledge;

public class Hello {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getName());
    }
}
