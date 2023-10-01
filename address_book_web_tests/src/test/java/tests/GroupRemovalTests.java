package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void removeGroupTest() {
        appMan.openGroupsPage();
        if(!appMan.isGroupPresent()){
            appMan.createGroup(new GroupData("", "", ""));
        }
        appMan.removeGroup();
    }

}
