package ca.ana.adressbook.tests;

import ca.ana.adressbook.model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void createGroupTest() {
        appMan.groups().createGroup(new GroupData("name", "header", "footer"));
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        appMan.groups().createGroup(new GroupData());
    }

    @Test
    public void createGroupWithNameOnlyTest() {
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some_name");
        appMan.groups().createGroup(groupWithName);
    }

}
