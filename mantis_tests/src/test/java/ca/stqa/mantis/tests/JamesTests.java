package ca.stqa.mantis.tests;

import ca.stqa.mantis.common.Utils;
import org.junit.jupiter.api.Test;

public class JamesTests extends TestBase{

    @Test
    public void canAddUser(){
        appMan.jamesCli().addUser(String.format("%s@localhost", Utils.randomString(5)), "password");
    }
}
