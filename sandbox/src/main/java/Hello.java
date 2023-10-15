import java.io.File;

public class Hello {

    public static void main(String[] args) {
        try {
            int x = 1;
            int y = 0;
            int z = divide(x, y);
            System.out.println(z);
        } catch (ArithmeticException exception) {
            System.out.println("Division by zero is not allowed");
        }
        System.out.println("Hello, world!");

        var configfile = new File("sandbox/build.gradle");
        System.out.println(configfile.getAbsolutePath());
        System.out.println(configfile.exists());
    }

    private static int divide(int x, int y) {
        int z = x / y;
        return z;
    }


}
