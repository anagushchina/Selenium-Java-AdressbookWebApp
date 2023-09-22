package ca.ana.geometry.figures;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.a, this.a) == 0 && Double.compare(rectangle.b, this.b) == 0
                || Double.compare(rectangle.a, this.b) == 0 && Double.compare(rectangle.b, this.a) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
