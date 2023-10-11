package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTest extends TestBase{

    @Test
    void modifyContactTest(){
        if(appMan.initContactHelper().getCount() == 0){
            appMan.initContactHelper().createContact(new ContactData().withName("first", "last"));
        }
        List<ContactData> oldContacts = appMan.initContactHelper().getList();
        var index = new Random().nextInt(oldContacts.size());
        var modifyingContact = oldContacts.get(index);
        var modifiedContact = new ContactData().withName("modified first", "modified last");
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
