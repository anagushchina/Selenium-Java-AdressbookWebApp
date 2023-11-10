package tests;

import common.Utils;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TestBase {

    @Test
    public void checkPhonesOfSingleContactTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData()
                    .withHomePhone(Utils.randomNumber(10))
                    .withMobilePhone(Utils.randomNumber(10))
                    .withWorkPhone(Utils.randomNumber(10))
                    .withSecondaryPhone(Utils.randomNumber(10)));
            appMan.refreshPage();
        }
        var contacts = appMan.initHbm().getContactsList();
        var contact = contacts.get(0);
        var phonesOnMainPage = appMan.initContactHelper().getPhonesOnMainPage(contact);
        appMan.initContactHelper().openEditContactForm(contact);
        var phonesOnEditPage = appMan.initContactHelper().getPhonesOnEditPage();
        Assertions.assertEquals(phonesOnMainPage, phonesOnEditPage);
    }

    @Test
    public void checkAddressOfSingleContactTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData()
                    .withFirstName(Utils.randomString(5))
                    .withLastName(Utils.randomString(10))
                    .withAddress(Utils.randomString(6)));
            appMan.refreshPage();
        }
        var contacts = appMan.initHbm().getContactsList();
        var contact = contacts.get(0);
        var addressOnMainPage = appMan.initContactHelper().getAddressOnMainPage(contact);
        appMan.initContactHelper().openEditContactForm(contact);
        var addressOnEditPage = appMan.initContactHelper().getAddressOnEditPage();
        Assertions.assertEquals(addressOnMainPage, addressOnEditPage);
    }

    @Test
    public void checkEmailsOfSingleContactTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(new ContactData()
                    .withFirstName(Utils.randomString(5))
                    .withLastName(Utils.randomString(10))
                    .withEmail(Utils.randomString(6)+"@mail.ca"));
            appMan.refreshPage();
        }
        var contacts = appMan.initHbm().getContactsList();
        var contact = contacts.get(0);
        var emailsOnMainPage = appMan.initContactHelper().getEmailsOnMainPage(contact);
        appMan.initContactHelper().openEditContactForm(contact);
        var emailsOnEditPage = appMan.initContactHelper().getEmailsOnEditPage();
        Assertions.assertEquals(emailsOnMainPage, emailsOnEditPage);
    }


    @Test
    public void checkPhonesOfSingleContactWithDBTest() {
        var contacts = appMan.initHbm().getContactsList();
        var contact = contacts.get(0);
        var phones = appMan.initContactHelper().getPhonesOnMainPage(contact);
        var expected = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                .filter(phone -> phone != null && !phone.equals(""))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    //    gets list of contacts from DB, gets map id-phones from UI and then perform comparison for each contact (using loop)
    @Test
    public void checkPhonesOfAllContactsTest1() {
        var contacts = appMan.initHbm().getContactsList();
        Map<String, String> phones = appMan.initContactHelper().getPhonesOnMainPage();
        for (var contact : contacts) {
            var expected = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                    .filter(phone -> phone != null && !phone.equals(""))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }
    }

    //    gets list of contacts from DB, converts it into map id-phones and then compares it with map id-phones received from UI
    @Test
    public void checkPhonesOfAllContactsTest2() {
        var contacts = appMan.initHbm().getContactsList();
        Map<String, String> expected = contacts.stream().collect(Collectors.toMap(
                contact -> contact.id(),
                contact -> Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(phone -> phone != null && !phone.equals(""))
                        .collect(Collectors.joining("\n"))));
        Map<String, String> phones = appMan.initContactHelper().getPhonesOnMainPage();
        Assertions.assertEquals(expected, phones);
    }
}
