package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class GroupCreationTests extends TestBase{

    public static List<String> groupNamesProvider() {
        var result = new ArrayList<>(List.of("some name 1", "some name 2"));
        for(int i=1; i<=5; i++ ){
            result.add(randomString(7));
        }
        return result;
    }

    public static List<GroupData> groupsProvider() {
        var result = new ArrayList<GroupData>();
        for(var name: List.of("", "name")){
            for(var header: List.of("", "header")){
                for(var footer: List.of("", "footer")){
                    result.add(new GroupData(name, header, footer));
                }
            }
        }
        for(int i=1; i<=5; i++ ){
            result.add(new GroupData(randomString(i*10), randomString(i*5), randomString(i*4)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupsProvider() {
        return new ArrayList<GroupData>(List.of(
                new GroupData("name'", "","")));
    }

    @Test
    public void createGroupTest() {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(new GroupData("name", "header", "footer"));
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        appMan.initGroupHelper().createGroup(new GroupData());
    }

    @Test
    public void createGroupWithNameOnlyTest() {
        appMan.initGroupHelper().createGroup(new GroupData().withName("name only"));
    }

    @ParameterizedTest
    @MethodSource("groupNamesProvider")
    public void createGroupsWithRandomNameTest(String name) {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(new GroupData(name, "header", "footer"));
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("groupsProvider")
    public void createMultipleGroupsTest(GroupData group) {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(group);
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupsProvider")
    public void cannotCreateGroupTest(GroupData group) {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(group);
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }
}
