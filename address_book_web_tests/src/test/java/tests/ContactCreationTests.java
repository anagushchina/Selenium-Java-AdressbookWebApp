package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @ParameterizedTest
    @MethodSource("contactsProvider")
    public void createMultipleContactsTest(ContactData contact){
        List<ContactData> oldContacts = appMan.initContactHelper().getList();
        appMan.initContactHelper().createContact(contact);
        List<ContactData> newContacts = appMan.initContactHelper().getList();
        Comparator<ContactData> compareByLastName = (o1, o2) ->
                CharSequence.compare(o1.lastName(), o2.lastName());
        newContacts.sort(compareByLastName);
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.add(contact.withAddress("").withMobilePhone("").withEmail(""));
        expectedContacts.sort(compareByLastName);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
    public static List<ContactData> contactsProvider() {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", randomString(5))) {
//            for (var lastName : List.of("", randomString(5))) {
//                for (var address : List.of("", randomString(10))) {
//                    for (var mobilePhone : List.of("", randomNumber(10))) {
//                        for (var email : List.of("", randomString(6)+"@mail.ca"))
//                            result.add(new ContactData().withMinSetOfData(firstName, lastName, address, mobilePhone, email));
//                    }
//                }
//            }
//        }
        for(int i=1; i<=3; i++ ){
            result.add(new ContactData().withMinSetOfData(randomString(i*3), randomString(i*3), randomString(i*10), randomNumber(10),randomString(i*5)));
        }
        return result;
    }

    @Test
    public void createEmptyContactTest() {
        appMan.initContactHelper().createContact(new ContactData());
    }

    @Test
    public void createContactWithNameTest() {
        appMan.initContactHelper().createContact(new ContactData().withName("First", "Last"));
    }


}
