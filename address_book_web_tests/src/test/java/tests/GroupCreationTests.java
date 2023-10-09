package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase{

    @ParameterizedTest
    @MethodSource("groupsProvider")
    public void createMultipleGroupsTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById);
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedGroups.sort(compareById);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupsProvider")
    public void cannotCreateGroupTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

    public static List<GroupData> groupsProvider() {
        var result = new ArrayList<GroupData>();
        for(var name: List.of("", "name")){
            for(var header: List.of("", "header")){
                for(var footer: List.of("", "footer")){
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for(int i=1; i<=5; i++ ){
            result.add(new GroupData()
                    .withName(randomString(i*10))
                    .withHeader(randomString(i*5))
                    .withFooter(randomString(i*4)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupsProvider() {
        return new ArrayList<>(List.of(
                new GroupData("", "name'", "","")));
    }





    @ParameterizedTest
    @MethodSource("groupNamesProvider")
    public void createGroupsWithRandomNameTest(String name) {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(new GroupData("", name, "header", "footer"));
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }
    public static List<String> groupNamesProvider() {
        var result = new ArrayList<>(List.of("some name 1", "some name 2"));
        for(int i=1; i<=5; i++ ){
            result.add(randomString(7));
        }
        return result;
    }




    @Test
    public void createGroupTest() {
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().createGroup(new GroupData("", "name", "header", "footer"));
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




}
