package tests;

import common.Utils;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupModificationTests extends TestBase {

//    precondition (group creation) and getting list of groups from DB, using Hibernate library
    @Test
    void modifyGroupHbmTest() {
        if (appMan.initHbm().getGroupCount() == 0) {
            appMan.initHbm().createGroup(new GroupData());
        }
        List<GroupData> oldGroups = appMan.initHbm().getGroupList();
        var index = new Random().nextInt(oldGroups.size());
        var modifyingGroup = oldGroups.get(index);
        var modifiedGroup = new GroupData().withName("modified" + Utils.randomString(5));
        appMan.initGroupHelper().modifyGroup(modifyingGroup, modifiedGroup);
        List<GroupData> newGroups = appMan.initHbm().getGroupList();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.set(index, modifiedGroup.withId(modifyingGroup.id()));
        Assertions.assertEquals(Set.copyOf(expectedGroups), Set.copyOf(newGroups));
    }



//    all actions via UI
    @Test
    void modifyGroupTest() {
        if (appMan.initGroupHelper().getCount() == 0) {
            appMan.initGroupHelper().createGroup(new GroupData().withName("some name"));
        }
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        var index = new Random().nextInt(oldGroups.size());
        var modifyingGroup = oldGroups.get(index);
        var modifiedGroup = new GroupData().withName("modified name");
        appMan.initGroupHelper().modifyGroup(modifyingGroup, modifiedGroup);
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.set(index, modifiedGroup.withId(modifyingGroup.id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedGroups.sort(compareById);
        Assertions.assertEquals(expectedGroups, newGroups);
    }


}
