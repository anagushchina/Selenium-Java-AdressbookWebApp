package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void deleteContact() {
        selectFirstContact();
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

    private void selectFirstContact() {
        click(By.name("selected[]"));
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
}
