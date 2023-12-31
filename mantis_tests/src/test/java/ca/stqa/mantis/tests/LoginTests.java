package ca.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase{

    @Test
    public void canLoginViaWebTest(){
        appMan.web().login("administrator", "root");
        Assertions.assertTrue(appMan.web().isLoggedIn());
    }

    @Test
    public void canLoginViaHttpTest(){
        appMan.http().login("administrator", "root");
        Assertions.assertTrue(appMan.http().isLoggedIn());
    }
}
