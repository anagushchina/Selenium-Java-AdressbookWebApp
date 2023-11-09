package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase{

//    all actions via UI
    @Test
    public void removeGroupTest() {
        if(appMan.initGroupHelper().getCount() == 0){
            appMan.initGroupHelper().createGroup(new GroupData());
        }
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        var index = new Random().nextInt(oldGroups.size());
        appMan.initGroupHelper().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.remove(index);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

//    creation of group and getting list of groups from DB, using Hibernate library
    @Test
    public void removeGroupHbmTest() {
        if(appMan.initHbm().getGroupCount() == 0){
            appMan.initHbm().createGroup(new GroupData());
        }
        List<GroupData> oldGroups = appMan.initHbm().getGroupList();
        var index = new Random().nextInt(oldGroups.size());
        appMan.initGroupHelper().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = appMan.initHbm().getGroupList();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.remove(index);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    @Test
    public void removeAllGroupsAtOnce(){
        if(appMan.initHbm().getGroupCount() == 0){
            appMan.initHbm().createGroup(new GroupData());
        }
        appMan.initGroupHelper().removeAllGroups();
        Assertions.assertEquals(0, appMan.initHbm().getGroupCount());
    }

}
