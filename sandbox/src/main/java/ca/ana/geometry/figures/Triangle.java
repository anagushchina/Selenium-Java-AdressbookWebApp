package ca.ana.geometry.figures;

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
}
