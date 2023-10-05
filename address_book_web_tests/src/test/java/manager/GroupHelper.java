package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager){
        super(manager);
    }

    public void openGroupsPage() {
        if(! manager.isElementPresent(By.name("new"))){
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void removeGroup() {
        openGroupsPage();
        selectFirstGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }

    private void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void modifyGroup(GroupData group) {
        openGroupsPage();
        selectFirstGroup();
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectFirstGroup() {
        click(By.name("selected[]"));
    }

}
