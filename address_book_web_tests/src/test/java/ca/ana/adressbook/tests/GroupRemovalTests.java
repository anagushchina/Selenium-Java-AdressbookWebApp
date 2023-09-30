package ca.ana.adressbook.tests;

import ca.ana.adressbook.model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupRemovalTests extends TestBase {

    @Test
    public void removeGroupTest() {
        if(!appMan.groups().isGroupPresent()){
            appMan.groups().createGroup(new GroupData("", "", ""));
        }
        appMan.groups().removeGroup();
    }

}
