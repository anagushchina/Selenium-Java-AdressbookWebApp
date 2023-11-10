package ca.ana;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {

    @Test
    public void sqrtTest(){
        var input = 4;
        var result = Math.sqrt(input);
        var reverse = result * result;
        Assertions.assertEquals(input, reverse, 0.000001);
    }

    @Test
    public void sortTest(){
        var list = new ArrayList<>(List.of(0,7,3,9,4));
        list.sort(Integer::compareTo);
        for (int i=0; i<list.size()-1; i++){
            Assertions.assertTrue(list.get(i)<=list.get(i+1));
        }
    }
}
