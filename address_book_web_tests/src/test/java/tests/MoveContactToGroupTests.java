package tests;

import common.Utils;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class MoveContactToGroupTests extends TestBase{

    @Test
    public void moveContactToGroupHbmTest(){
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
        if(appMan.initHbm().getContactsInGroup(group).contains(contact)){
            appMan.initContactHelper().removeContactFromGroup(contact, group);
        }
        var oldContactsInGroup = appMan.initHbm().getContactsInGroup(group);
        appMan.initContactHelper().addContactToGroup(contact, group);
        var newContactsInGroup = appMan.initHbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) ->
        {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactsInGroup.sort(compareById);
        var expectedContacts = new ArrayList<>(oldContactsInGroup);
        expectedContacts.add(contact.withId(newContactsInGroup.get(newContactsInGroup.size() - 1).id()));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContactsInGroup);
    }
}
