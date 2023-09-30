package ca.ana.adressbook.tests;

import ca.ana.adressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public static ApplicationManager appMan;

    @BeforeEach
    public void setUp() {
        if (appMan == null){
            appMan = new ApplicationManager();
        }
        appMan.initDriver();
    }

}
