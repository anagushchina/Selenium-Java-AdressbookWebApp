package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupCreationTests extends TestBase{

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

    @Test
    public void createMultipleGroupsTest() {
        int n = 5;
        int groupCount = appMan.initGroupHelper().getCount();
        for(int i=1; i<=5; i++ ){
            appMan.initGroupHelper().createGroup(new GroupData(randomString(7), "header", "footer"));
        }
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount + n, newGroupCount);
    }
}
