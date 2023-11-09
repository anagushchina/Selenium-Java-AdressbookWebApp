package tests;

import common.Utils;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class RemoveContactFromGroupTests extends TestBase{

    @Test
    public void RemoveContactFromGroupHbmTest(){
        if (appMan.initHbm().getGroupCount() == 0) {
            appMan.initHbm().createGroup(new GroupData("", Utils.randomString(5), Utils.randomString(5), Utils.randomString(5)));
            appMan.refreshPage();
        }
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData().withFirstName(Utils.randomString(5)).withLastName(Utils.randomString(7)).withAddress(Utils.randomString(15)));
            appMan.refreshPage();
        }
        var group = appMan.initHbm().getGroupList().get(0);
        var contact = appMan.initHbm().getContactsList().get(0);
        if(!appMan.initHbm().getContactsInGroup(group).contains(contact)){
            appMan.initContactHelper().addContactToGroup(contact, group);
        }
        var oldContactsInGroup = appMan.initHbm().getContactsInGroup(group);
        appMan.initContactHelper().removeContactFromGroup(contact, group);
        var newContactsInGroup = appMan.initHbm().getContactsInGroup(group);

        var expectedContactsInGroup = new ArrayList<>(oldContactsInGroup);
        expectedContactsInGroup.remove(contact);
        Assertions.assertEquals(expectedContactsInGroup, newContactsInGroup);
    }
}
