package ca.ana.geometry.figures;

public record Rectangle(double a, double b) {

    public Rectangle{
        if (a<0 || b<0){
            throw new IllegalArgumentException("Rectangle sides cannot be negative");
        }
    }


    public static void PrintRectangleArea(double a, double b) {
        String text = String.format("Area of rectangle with the sides %f and %f is equal %f", a, b, calculateArea(a, b));
        System.out.println(text);

    }

    public static double calculateArea(double a, double b) {
        return a * b;
    }
}
