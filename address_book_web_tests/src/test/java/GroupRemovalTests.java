
import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void removeGroupTest() {
        openGroupsPage();
        if(!isGroupPresent()){
            createGroup(new GroupData("", "", ""));
        }
        removeGroup();
    }

}
