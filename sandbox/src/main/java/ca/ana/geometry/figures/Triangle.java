package ca.ana.geometry.figures;

import java.util.Objects;

public record Triangle(double side1, double side2, double side3) {

    public Triangle{
        if(side1<0 || side2<0 || side3<0){
            throw new IllegalArgumentException("Triangle sides lengths must be positive");
        }
        if((side1+side2)<side3 || (side1+side3)<side2 || (side2+side3)<side1){
            throw new IllegalArgumentException("The sum of the lengths of any 2 sides of a triangle must be greater than the third side.");
        }
    }

    public double calculatePerimeter(){
        return this.side1+this.side2+this.side3;
    }

    public double calculateArea(){
        double semiS = calculatePerimeter()/2;
        double area = Math.sqrt(semiS*(semiS-this.side1)*(semiS-this.side2)*(semiS-this.side3));
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.side1, side1) == 0 && Double.compare(triangle.side2, side2) == 0 && Double.compare(triangle.side3, side3) == 0
        || Double.compare(triangle.side1, side1) == 0 && Double.compare(triangle.side2, side3) == 0 && Double.compare(triangle.side3, side2) == 0
        || Double.compare(triangle.side1, side3) == 0 && Double.compare(triangle.side2, side2) == 0 && Double.compare(triangle.side3, side1) == 0
        || Double.compare(triangle.side1, side2) == 0 && Double.compare(triangle.side2, side1) == 0 && Double.compare(triangle.side3, side3) == 0
        || Double.compare(triangle.side1, side3) == 0 && Double.compare(triangle.side2, side1) == 0 && Double.compare(triangle.side3, side2) == 0
        || Double.compare(triangle.side1, side2) == 0 && Double.compare(triangle.side2, side3) == 0 && Double.compare(triangle.side3, side1) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
