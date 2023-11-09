package tests;

import common.Utils;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTest extends TestBase {

    //precondition (group creation) and getting list of groups from DB, using Hibernate library
    @Test
    void modifyContactHbmTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData().withFullName(Utils.randomString(5), Utils.randomString(8)));
            appMan.refreshPage();
        }
        List<ContactData> oldContacts = appMan.initHbm().getContactsList();
        var index = new Random().nextInt(oldContacts.size());
        var modifyingContact = oldContacts.get(index);
        var modifiedContact = new ContactData().withFullName("modified first name", "modified last name");
        appMan.initContactHelper().modifyContact(modifyingContact, modifiedContact);
        List<ContactData> newContacts = appMan.initHbm().getContactsList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.set(index, modifiedContact.withId(modifyingContact.id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }


    //all actions via UI
    @Test
    void modifyContactTest() {
        if (appMan.initContactHelper().getCount() == 0) {
            appMan.initContactHelper().createContact(new ContactData().withFullName("first", "last"));
        }
        List<ContactData> oldContacts = appMan.initContactHelper().getList();
        var index = new Random().nextInt(oldContacts.size());
        var modifyingContact = oldContacts.get(index);
        var modifiedContact = new ContactData().withFullName("modified first", "modified last");
        appMan.initContactHelper().modifyContact(modifyingContact, modifiedContact);
        List<ContactData> newContacts = appMan.initContactHelper().getList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.set(index, modifiedContact.withId(modifyingContact.id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}
