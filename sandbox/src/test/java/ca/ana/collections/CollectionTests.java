package ca.ana.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {

    @Test
    void arrayTest() {
        var array = new String[]{"a", "b", "c"};
        array[0] = "d";
        Assertions.assertEquals(array[0], "d");

        //create empty array with specified length
        var emptyArray = new String[3];
        Assertions.assertEquals(emptyArray.length, 3);
    }

    @Test
    void listTest() {
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

    @Test
    void setTest() {
        var set = Set.of("a", "b", "c");
        Assertions.assertEquals(3, set.size());
        var setFromList = Set.copyOf(List.of("a", "b", "c"));
        Assertions.assertEquals(3, setFromList.size());
//        get some element from set, but not specific one
        var element = set.stream().findAny().get();

        var modifiedSet = new HashSet<>(List.of("a", "b", "c"));
        modifiedSet.add("d");
        Assertions.assertEquals(4, modifiedSet.size());
    }

    @Test
    void mapTest(){
        var map = new HashMap<Character, String>();
        map.put('1', "one");
        map.put('2', "two");
        map.put('3', "three");
        Assertions.assertEquals("one", map.get('1'));
        map.put('1', "uno");
        Assertions.assertEquals("uno", map.get('1'));

    }

}
