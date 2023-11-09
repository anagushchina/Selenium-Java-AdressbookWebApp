package ca.ana.geometry.figures;

public record Square(double side) {

//    private double side;
//
//    public Square(double side) {
//        this.side = side;
//    }

    public Square{
        if(side<0){
            throw new IllegalArgumentException("Square side cannot be negative");
        }
    }


    public static void printSquareArea(Square s){
        String text = String.format("Area of square with the side %f is equal %f", s.side, s.calculateArea());
        System.out.println(text);
    }

    public static void printPerimeter(Square square) {
        String text = String.format("Perimeter of square with the side %f is equal %f", square.side, square.calculatePerimeter());
        System.out.println(text);
    }

//    public static double calculateArea(double side) {
//        return side * side;
//    }
//
//    public static double calculatePerimeter(double i) {
//        return i*4;
//    }

    public double calculateArea() {
        return this.side * this.side;
    }

    public double calculatePerimeter() {
        return this.side*4;
    }
}
