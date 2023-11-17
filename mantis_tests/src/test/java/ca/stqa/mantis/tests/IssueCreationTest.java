package ca.stqa.mantis.tests;

import ca.stqa.mantis.common.Utils;
import ca.stqa.mantis.model.IssueData;
import org.junit.jupiter.api.Test;

public class IssueCreationTest extends TestBase{

    @Test
    void canCreateIssue(){
        appMan.rest().createIssue(new IssueData()
                .withSummary(Utils.randomString(10))
                .withDescription(Utils.randomString(50))
                .withProject(1L));
    }
}
