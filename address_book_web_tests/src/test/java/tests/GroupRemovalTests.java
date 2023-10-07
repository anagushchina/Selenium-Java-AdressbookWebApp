package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void removeGroupTest() {
        if(appMan.initGroupHelper().getCount() == 0){
            appMan.initGroupHelper().createGroup(new GroupData());
        }
        int groupCount = appMan.initGroupHelper().getCount();
        appMan.initGroupHelper().removeGroup();
        int newGroupCount = appMan.initGroupHelper().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    public void removeAllGroupsAtOnce(){
        if(appMan.initGroupHelper().getCount() == 0){
            appMan.initGroupHelper().createGroup(new GroupData());
        }
        appMan.initGroupHelper().removeAllGroups();
        Assertions.assertEquals(0, appMan.initGroupHelper().getCount());
    }

}
