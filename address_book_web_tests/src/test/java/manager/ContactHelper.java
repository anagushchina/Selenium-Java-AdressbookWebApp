package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        deleteSelectedContacts();
        returnToHomePage();
    }

    public boolean isContactPresent(){
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void returnToHomePage() {
        click(By.xpath("//a[text()='home page']"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
    }

    private void initContactCreation() {
        click(By.xpath("//a[text()='add new']"));
    }

    private void deleteSelectedContacts() {
        click(By.cssSelector("input[value=Delete]"));
    }

    private void selectContact(ContactData contact) {
        click(By.xpath(String.format(
                "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td",
                contact.firstName(),
                contact.lastName())));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for(var checkbox: checkboxes){
            checkbox.click();
        }
        deleteSelectedContacts();
        manager.driver.switchTo().alert().accept();
    }

    public List<ContactData> getList() {
        var contactList = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for(var entry: entries){
            var lastName = entry.findElement(By.xpath(".//td[2]")).getText();
            var firstName = entry.findElement(By.xpath(".//td[3]")).getText();
            contactList.add(new ContactData().withName(firstName, lastName));
        }
        return contactList;
    }
}
