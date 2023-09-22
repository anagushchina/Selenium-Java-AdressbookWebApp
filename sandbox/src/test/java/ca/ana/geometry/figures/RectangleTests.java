package ca.ana.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void cannotCreateRectangleWithNegativeSide(){
        try{
            new Rectangle(-7.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    void equalRectangles(){
        var r1 = new Rectangle(3.0, 5.0);
        var r2 = new Rectangle(3.0, 5.0);
        Assertions.assertEquals(r1, r2);
        Assertions.assertTrue(r1.equals(r2));
    }
    @Test
    void equalRotatedRectangles(){
        var r1 = new Rectangle(3.0, 5.0);
        var r2 = new Rectangle(5.0, 3.0);
        Assertions.assertEquals(r1, r2);
        Assertions.assertTrue(r1.equals(r2));
    }
}
