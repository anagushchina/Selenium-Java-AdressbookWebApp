package ca.ana.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        var result = s.calculateArea();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(12.0, new Square(3.0).calculatePerimeter());
    }
}
