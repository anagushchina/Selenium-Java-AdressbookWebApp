package ca.ana.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTest(){
        var array = new String[]{"a", "b", "c"};
        array[0]="d";
        Assertions.assertEquals(array[0], "d");

        //create empty array with specified length
        var emptyArray = new String[3];
        Assertions.assertEquals(emptyArray.length, 3);
    }

    @Test
    void listTest(){
        var list = new ArrayList<String>();
        Assertions.assertEquals(list.size(), 0);
        list.add("a");
        list.add("b");
        list.add("c");
        Assertions.assertEquals(list.size(), 3);

        list.set(0, "d");
        Assertions.assertEquals(list.get(0), "d");

        var listWithElements = new ArrayList<>(List.of("a", "b", "c"));
        listWithElements.add("d");
        Assertions.assertEquals(listWithElements.size(), 4);



    }

}
