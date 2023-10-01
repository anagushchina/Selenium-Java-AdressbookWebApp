package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupCreationTests extends TestBase{

    @Test
    public void createGroupTest() {
        appMan.initGroupHelper().createGroup(new GroupData("name", "header", "footer"));
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
