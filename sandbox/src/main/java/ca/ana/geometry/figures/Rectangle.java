package ca.ana.geometry.figures;

public record Rectangle(double a, double b) {


    public static void PrintRectangleArea(double a, double b) {
        String text = String.format("Area of rectangle with the sides %f and %f is equal %f", a, b, calculateArea(a, b));
        System.out.println(text);

    }

    public static double calculateArea(double a, double b) {
        return a * b;
    }
}
