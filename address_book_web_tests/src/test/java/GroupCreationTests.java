import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class GroupCreationTests extends TestBase{

    @Test
    public void createGroupTest() {
        openGroupsPage();
        createGroup(new GroupData("name", "header", "footer"));
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void createGroupWithNameOnlyTest() {
        openGroupsPage();
        createGroup(new GroupData().withName("name only"));
    }
}
