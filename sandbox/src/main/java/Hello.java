public class Hello {

    public static void main(String[] args) {
        try {
            int x = 1;
            int y = 0;
            int z = divide(x, y);
            System.out.println(z);
        } catch (ArithmeticException exception) {
            System.out.println("Division by zero is not allowed");;
        }
        System.out.println("Hello, world!");
    }

    private static int divide(int x, int y) {
        int z = x / y;
        return z;
    }


}
