package ca.ana.adressbook.tests;

import ca.ana.adressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void modifyGroupTest(){
        if(!appMan.groups().isGroupPresent()){
            appMan.groups().createGroup(new GroupData("", "", ""));
        }
        appMan.groups().modifyGroup(new GroupData().withName("modified_name"));

    }
}
