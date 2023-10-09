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
        expectedContacts.set(index, modifiedContact.withName("modified first", "modified last"));
        Comparator<ContactData> compareByLastName = (o1, o2) ->
                CharSequence.compare(o1.lastName(), o2.lastName());
        newContacts.sort(compareByLastName);
        expectedContacts.sort(compareByLastName);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}
