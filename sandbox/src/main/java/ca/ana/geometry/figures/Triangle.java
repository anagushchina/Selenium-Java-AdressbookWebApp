package ca.ana.geometry.figures;

public record Triangle(double side1, double side2, double side3) {

    public double calculatePerimeter(){
        return this.side1+this.side2+this.side3;
    }

    public double calculateArea(){
        double semiS = calculatePerimeter()/2;
        double area = Math.sqrt(semiS*(semiS-this.side1)*(semiS-this.side2)*(semiS-this.side3));
        return area;
    }
}
