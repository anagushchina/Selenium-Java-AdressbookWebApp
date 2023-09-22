package ca.ana.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        var result = s.calculateArea();
//        Assertions.assertEquals(25.0, result);
        if(result != 25.0){
            throw new AssertionError(String.format("Expected %f, actual %f", 25.0, result));
        }
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(12.0, new Square(3.0).calculatePerimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try{
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    void equalSquares(){
        var s1 = new Square(3.0);
        var s2 = new Square(3.0);
        Assertions.assertEquals(s1, s2);
        Assertions.assertTrue(s1.equals(s2));
    }

    @Test
    void notEqualSquares(){
        var s1 = new Square(4.0);
        var s2 = new Square(3.0);
        Assertions.assertNotEquals(s1, s2);
        Assertions.assertTrue(!s1.equals(s2));
    }
}
