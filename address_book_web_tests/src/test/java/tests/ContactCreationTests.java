package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.Utils;
import io.qameta.allure.Allure;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    //getting list of groups from DB, using Hibernate library
    @ParameterizedTest
    @MethodSource("contactsProvider")
    public void createMultipleContactsHbmTest(ContactData contact) {
        List<ContactData> oldContacts = appMan.initHbm().getContactsList();
        appMan.initContactHelper().createContact(contact);
        List<ContactData> newContacts = appMan.initHbm().getContactsList();
        Comparator<ContactData> compareById = (o1, o2) ->
        {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }


    @Test
    public void createContactInGroupTest() {
        ContactData contact = new ContactData()
                .withFirstName(Utils.randomString(5))
                .withLastName(Utils.randomString(7))
                .withAddress(Utils.randomString(15));
        Allure.step("Checking precondition", step -> {
            if (appMan.initHbm().getGroupCount() == 0) {
            appMan.initHbm().createGroup(new GroupData("", Utils.randomString(5), Utils.randomString(5), Utils.randomString(5)));
        }});

        var group = appMan.initHbm().getGroupList().get(0);
        var oldContactsInGroup = appMan.initHbm().getContactsInGroup(group);
        appMan.initContactHelper().createContact(contact, group);
        var newContactsInGroup = appMan.initHbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) ->
        {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactsInGroup.sort(compareById);
        var expectedContacts = new ArrayList<>(oldContactsInGroup);
        expectedContacts.add(contact.withId(newContactsInGroup.get(newContactsInGroup.size() - 1).id()));
        expectedContacts.sort(compareById);
        Allure.step("Validating results", step ->{
            Assertions.assertEquals(expectedContacts, newContactsInGroup);});
    }

    @Test
    public void createEmptyContactTest() {
        appMan.initContactHelper().createContact(new ContactData());
    }

    @Test
    public void createContactWithPhoto() {
        var contact = new ContactData()
                .withLastName(Utils.randomString(10))
                .withFirstName(Utils.randomString(5))
                .withPhoto(Utils.randomFile("src/test/resources/images"));
        appMan.initContactHelper().createContact(contact);
    }


    //all actions via UI using contact data from file
//    @ParameterizedTest
//    @MethodSource("contactsFromFileProvider")
//    public void createMultipleContactsTest(ContactData contact) {
//        List<ContactData> oldContacts = appMan.initContactHelper().getList();
//        appMan.initContactHelper().createContact(contact);
//        List<ContactData> newContacts = appMan.initContactHelper().getList();
//        Comparator<ContactData> compareById = (o1, o2) ->
//        {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//        var expectedContacts = new ArrayList<>(oldContacts);
//        expectedContacts.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withAddress("").withMobilePhone("").withEmail(""));
//        expectedContacts.sort(compareById);
//        Assertions.assertEquals(expectedContacts, newContacts);
//    }


    public static List<ContactData> contactsProvider() {
        var result = new ArrayList<ContactData>();
        result.add(new ContactData()
                .withFirstName(Utils.randomString(5))
                .withMiddleName(Utils.randomString(5))
                .withLastName(Utils.randomString(7))
                .withCompany(Utils.randomString(5).toUpperCase())
                .withAddress(Utils.randomString(15))
                .withMobilePhone(Utils.randomNumber(10))
                .withWorkPhone(Utils.randomNumber(10))
                .withEmail(Utils.randomString(5) + "@domain.do")
                .withEmail3(Utils.randomString(5) + "@domain.do"));
        result.add(new ContactData()
                .withFirstName(Utils.randomString(5))
                .withLastName(Utils.randomString(7))
                .withAddress(Utils.randomString(15))
                .withHomePhone(Utils.randomNumber(10))
                .withMobilePhone(Utils.randomNumber(10))
                .withWorkPhone(Utils.randomNumber(10))
                .withEmail(Utils.randomString(5) + "@domain.do")
                .withEmail2(Utils.randomString(7) + "@domain.do")
                .withEmail3(Utils.randomString(9) + "@domain.do"));
        result.add(new ContactData()
                .withFirstName(Utils.randomString(5))
                .withLastName(Utils.randomString(7))
                .withAddress(Utils.randomString(15))
                .withMobilePhone(Utils.randomNumber(10))
                .withEmail(Utils.randomString(5) + "@domain.do"));
        return result;
    }


    public static List<ContactData> contactsFromFileProvider() throws IOException {
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
        var data = new File("contacts.xml");
        XmlMapper xmlMapper = new XmlMapper();
        var value = xmlMapper.readValue(data, new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }


}
