package ca.ana.geometry;

import ca.ana.geometry.figures.Rectangle;
import ca.ana.geometry.figures.Square;
import ca.ana.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        //Standard loop
        var squares = List.of(new Square(4), new Square(9), new Square(7));
        for (Square square : squares) {
            Square.printSquareArea(square);
        }

        //'forEach' function accepts another function 'print' as a parameter. Print is a variable with type Consumer<Square>
        // and contains a function that prints square area: in parentheses, we list parameters, in curly brackets - method's body
        Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
        };
        squares.forEach(print);


        //Shorter version. Since there is an only one parameter and function contains only one method,
//        which returns nothing, we can remove parentheses and curly brackets
        Consumer<Square> print1 = square -> Square.printSquareArea(square);
        squares.forEach(print1);


        //Shorter version. Since all that print2 function does is just calling another method, we can rewrite code as following
        Consumer<Square> print2 = Square::printSquareArea;
        squares.forEach(print2);

        //The shortest version
        squares.forEach(Square::printSquareArea);

//        Rectangle.PrintRectangleArea(4.0,6.0);
//
//        System.out.println(new Triangle(2.0,2.0,2.0).calculatePerimeter());
//        System.out.println(new Triangle(5.0,4.0,2.0).calculateArea());

        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squaresStream = Stream.generate(randomSquare).limit(5);
        Consumer<Square> printSquares = (square) -> {
            Square.printSquareArea(square);
            Square.printPerimeter(square);
        };
        squaresStream.peek(Square::printPerimeter).forEach(Square::printSquareArea);
    }

}
