package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroupWhenCreatingContact(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroupWhenCreatingContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        deleteSelectedContacts();
        confirmDeleting();
        waitForReturnToHomePage();
    }

    private void waitForReturnToHomePage() {
        manager.driver.findElement(By.cssSelector("div.msgbox"));
    }

    private void confirmDeleting() {
        manager.driver.switchTo().alert().accept();
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void returnToHomePage() {
        click(By.xpath("//a[text()='home']"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        if (!(contact.photo() == "")) {
            attach(By.name("photo"), contact.photo());
        }
    }


    private void initContactCreation() {
        click(By.xpath("//a[text()='add new']"));
    }

    private void deleteSelectedContacts() {
        click(By.cssSelector("input[value=Delete]"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
//        WebElement contactEntry = manager.driver.findElement(By.xpath(String.format(
//                "//tr[@name='entry' and child::td[text()='%s'] and child::td[text()='%s']]",
//                contact.lastName(),
//                contact.firstName()
//                )));
//        contactEntry.findElement(By.xpath(".//input[@type='checkbox']")).click();
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        deleteSelectedContacts();
        confirmDeleting();
        waitForReturnToHomePage();
    }

    public List<ContactData> getList() {
        var contactList = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var entry : entries) {
            var lastName = entry.findElement(By.xpath(".//td[2]")).getText();
            var firstName = entry.findElement(By.xpath(".//td[3]")).getText();
            var id = entry.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value");
            contactList.add(new ContactData().withId(id).withFullName(firstName, lastName));
        }
        return contactList;
    }

    public void modifyContact(ContactData modifyingContact, ContactData modifiedContact) {
        initContactModification(modifyingContact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactModification() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void initContactModification(ContactData contact) {
        WebElement contactEntry = manager.driver.findElement(By.xpath(String.format(
                "//input[@value='%s']//ancestor::tr[@name='entry']", contact.id())));
        contactEntry.findElement(By.xpath(".//img[@title='Edit']")).click();

    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContact(contact);
        selectGroupToMoveContact(group);
        addToSelectedGroup();
        returnToHomePage();
    }

    private void addToSelectedGroup() {
        manager.driver.findElement(By.name("add")).click();
    }

    private void selectGroupToMoveContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroupToViewContacts(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selectGroupToViewContacts(group);
        selectContact(contact);
        removeContact();
        manager.driver.findElement(By.cssSelector("div.msgbox"));
        returnToHomePage();
    }

    private void removeContact() {
        manager.driver.findElement(By.name("remove")).click();
    }


    public String getPhonesOnMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../..//td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhonesOnMainPage() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public void openEditContactForm(ContactData contact) {
        manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../..//td[8]", contact.id()))).click();
    }

    public String getPhonesOnEditPage() {
        var home = manager.driver.findElement(By.name("home")).getAttribute("value");
        var mobile = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        var work = manager.driver.findElement(By.name("work")).getAttribute("value");
        var secondaryHome = manager.driver.findElement(By.name("phone2")).getAttribute("value");

        var phones = Stream.of(home, mobile, work, secondaryHome)
                .filter(phone -> phone != null && !phone.equals(""))
                .collect(Collectors.joining("\n"));
        System.out.println(phones);
        return phones;
    }

    public String getAddressOnMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../..//td[4]", contact.id()))).getText();
    }

    public String getAddressOnEditPage() {
       return manager.driver.findElement(By.name("address")).getText();
    }

    public String getEmailsOnMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../..//td[5]", contact.id()))).getText();
    }

    public String getEmailsOnEditPage() {
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        var emails = Stream.of(email, email2, email3)
                .filter(phone -> phone != null && !phone.equals(""))
                .collect(Collectors.joining("\n"));
        System.out.println(emails);
        return emails;
    }
}
