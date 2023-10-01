import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class GroupCreationTests extends TestBase{

    @Test
    public void createGroupTest() {
        openGroupsPage();
        createGroup("name", "header", "footer");
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        openGroupsPage();
        driver.findElement(By.linkText("groups")).click();
        createGroup("", "", "");
    }
}
