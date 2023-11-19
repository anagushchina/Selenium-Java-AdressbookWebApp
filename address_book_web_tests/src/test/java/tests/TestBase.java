package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static ApplicationManager appMan;


    @BeforeEach
    public void setUp() throws IOException {
        if(appMan == null){
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            appMan = new ApplicationManager();
            appMan.initSession(System.getProperty("browser","chrome"), properties);
        }
    }

//    @AfterEach
//    public void checkDataBaseConsistency(){
//        appMan.initJdbcHelper().checkConsistency();
//    }

}
