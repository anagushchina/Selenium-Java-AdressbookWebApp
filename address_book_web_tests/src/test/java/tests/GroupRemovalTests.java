package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void removeGroupTest() {
        if(!appMan.initGroupHelper().isGroupPresent()){
            appMan.initGroupHelper().createGroup(new GroupData("", "", ""));
        }
        appMan.initGroupHelper().removeGroup();
    }

}
