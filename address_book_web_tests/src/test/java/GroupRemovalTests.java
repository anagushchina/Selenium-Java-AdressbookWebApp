
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void removeGroupTest() {
        openGroupsPage();
        if(!isGroupPresent()){
            createGroup("", "", "");
        }
        removeGroup();
    }

}
