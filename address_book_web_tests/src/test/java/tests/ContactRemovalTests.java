package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {


    //precondition (contact creation) and getting list of contacts from DB, using Hibernate library
    @Test
    public void removeContactHbmTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData());
            appMan.refreshPage();
        }
        List<ContactData> oldContacts = appMan.initHbm().getContactsList();
        var index = new Random().nextInt(oldContacts.size());
        appMan.initContactHelper().deleteContact(oldContacts.get(index));
        List<ContactData> newContacts = appMan.initHbm().getContactsList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void removeAllContactsAtOnceHbmTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData());
            appMan.refreshPage();
        }
        appMan.initContactHelper().deleteAllContacts();
        Assertions.assertEquals(0, appMan.initHbm().getContactCount());
    }


//    //all actions via UI
    @Test
    public void removeContactTest() {
        if (!appMan.initContactHelper().isContactPresent()) {
            appMan.initContactHelper().createContact(new ContactData());
        }
        List<ContactData> oldContacts = appMan.initContactHelper().getList();
        var index = new Random().nextInt(oldContacts.size());
        appMan.initContactHelper().deleteContact(oldContacts.get(index));
        List<ContactData> newContacts = appMan.initContactHelper().getList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void removeAllContactsAtOnceTest() {
        if (!appMan.initContactHelper().isContactPresent()) {
            appMan.initContactHelper().createContact(new ContactData());
        }
        appMan.initContactHelper().deleteAllContacts();
        Assertions.assertEquals(0, appMan.initContactHelper().getCount());
    }
}
