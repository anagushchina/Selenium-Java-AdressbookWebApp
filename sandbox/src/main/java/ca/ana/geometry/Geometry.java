package ca.ana.geometry;

import ca.ana.geometry.figures.Rectangle;
import ca.ana.geometry.figures.Square;
import ca.ana.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {

        Square.printSquareArea(new Square(4));
        Rectangle.PrintRectangleArea(4.0,6.0);

        System.out.println(new Triangle(2.0,2.0,2.0).calculatePerimeter());
        System.out.println(new Triangle(5.0,4.0,2.0).calculateArea());
    }

}
