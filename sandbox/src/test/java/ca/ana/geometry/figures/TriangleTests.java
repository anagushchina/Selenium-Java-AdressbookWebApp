package ca.ana.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    public void canCalculatePerimeter(){
        var t = new Triangle(3.0, 6.7, 7.3);
        Assertions.assertEquals(17, t.calculatePerimeter());

    }

    @Test
    public void canCalculateArea(){
        var t = new Triangle(5.0, 4.0, 8.0);
        Assertions.assertEquals(8.181534085976786, t.calculateArea());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide(){
        try{
            new Triangle(-7.0, 3.0, -4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    void checkTriangleInequality(){
        try{
            new Triangle(1.0, 9.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    void equalTriangles(){
        var t1 = new Triangle(3.0, 5.0, 4.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);
        Assertions.assertEquals(t1, t2);
        Assertions.assertTrue(t1.equals(t2));
    }
    @Test
    void equalRotatedTriangles(){
        var t1 = new Triangle(5,3,4);
        var t2 = new Triangle(4,3,5);
        Assertions.assertEquals(t1, t2);
        Assertions.assertTrue(t1.equals(t2));
    }
}
