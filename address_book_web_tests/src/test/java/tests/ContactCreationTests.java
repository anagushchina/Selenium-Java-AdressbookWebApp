package tests;

import common.Utils;
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
        Comparator<ContactData> compareById = (o1, o2) ->
        {return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));};
        newContacts.sort(compareById);
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withAddress("").withMobilePhone("").withEmail(""));
        expectedContacts.sort(compareById);
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
        for(int i=1; i<=4; i++ ){
            result.add(new ContactData().withMinSetOfData(Utils.randomString(i*3), Utils.randomString(i*3), Utils.randomString(i*10), Utils.randomNumber(10), Utils.randomString(i*5)));
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

    @Test
    public void createContactWithPhoto(){
        var contact = new ContactData()
                .withLastName(Utils.randomString(10))
                .withFirstName(Utils.randomString(5))
                .withPhoto(Utils.randomFile("src/test/resources/images"));
        appMan.initContactHelper().createContact(contact);
    }


}
