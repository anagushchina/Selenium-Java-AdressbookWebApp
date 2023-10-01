package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupCreationTests extends TestBase{

    @Test
    public void createGroupTest() {
        appMan.openGroupsPage();
        appMan.createGroup(new GroupData("name", "header", "footer"));
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        appMan.openGroupsPage();
        appMan.createGroup(new GroupData());
    }

    @Test
    public void createGroupWithNameOnlyTest() {
        appMan.openGroupsPage();
        appMan.createGroup(new GroupData().withName("name only"));
    }
}
