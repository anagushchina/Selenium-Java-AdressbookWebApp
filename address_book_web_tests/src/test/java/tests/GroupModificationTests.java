package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void modifyGroupTest(){
        if(appMan.initGroupHelper().getCount() == 0){
            appMan.initGroupHelper().createGroup(new GroupData().withName("some name"));
        }
        appMan.initGroupHelper().modifyGroup(new GroupData().withName("modified name"));
    }
}
