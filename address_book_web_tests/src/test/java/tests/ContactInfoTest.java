package tests;

import common.Utils;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TestBase {

    ContactData contact = new ContactData()
            .withFirstName(Utils.randomString(5))
            .withLastName(Utils.randomString(10))
            .withAddress(Utils.randomString(6))
            .withHomePhone(Utils.randomNumber(10))
            .withMobilePhone(Utils.randomNumber(10))
            .withWorkPhone(Utils.randomNumber(10))
            .withSecondaryPhone(Utils.randomNumber(10))
            .withEmail(Utils.randomString(6) + "@mail.ca")
            .withEmail2(Utils.randomString(6) + "@mail.ca");

    @Test
    public void checkInfoOfSingleContactTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(contact);
            appMan.refreshPage();
        }
        var contacts = appMan.initHbm().getContactsList();
        var contact = contacts.get(0);

        var phonesOnMainPage = appMan.initContactHelper().getPhonesOnMainPage(contact);
        var addressOnMainPage = appMan.initContactHelper().getAddressOnMainPage(contact);
        var emailsOnMainPage = appMan.initContactHelper().getEmailsOnMainPage(contact);

        appMan.initContactHelper().openEditContactForm(contact);

        var phonesOnEditPage = appMan.initContactHelper().getPhonesOnEditPage();
        var addressOnEditPage = appMan.initContactHelper().getAddressOnEditPage();
        var emailsOnEditPage = appMan.initContactHelper().getEmailsOnEditPage();

        Assertions.assertEquals(phonesOnMainPage, phonesOnEditPage);
        Assertions.assertEquals(addressOnMainPage, addressOnEditPage);
        Assertions.assertEquals(emailsOnMainPage, emailsOnEditPage);
    }

    @Test
    public void checkPhonesOfSingleContactWithDBTest() {
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(contact);
            appMan.refreshPage();
        }
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
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(contact);
            appMan.refreshPage();
        }
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
        if (appMan.initHbm().getContactCount() == 0) {
            appMan.initHbm().createContact(contact);
            appMan.refreshPage();
        }
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
