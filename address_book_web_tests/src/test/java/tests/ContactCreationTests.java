package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void createEmptyContactTest() {
        appMan.initContactHelper().createContact(new ContactData());
    }

    @Test
    public void createContactWithNameTest() {
        appMan.initContactHelper().createContact(new ContactData().withName("First", "Last"));
    }


}
