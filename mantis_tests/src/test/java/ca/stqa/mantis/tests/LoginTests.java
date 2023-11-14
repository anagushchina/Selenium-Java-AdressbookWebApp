package ca.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase{

    @Test
    public void canLogin(){
        appMan.session().login("administrator", "root");
        Assertions.assertTrue(appMan.session().isLoggedIn());
    }
}
