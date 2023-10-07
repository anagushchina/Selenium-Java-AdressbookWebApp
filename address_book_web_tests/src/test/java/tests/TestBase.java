package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    public static ApplicationManager appMan;


    @BeforeEach
    public void setUp() {
        if(appMan == null){
            appMan = new ApplicationManager();
        }
        appMan.initSession(System.getProperty("browser","chrome"));
    }

    public static String randomString(int n){
        var rnd = new Random();
        var result = "";
        for(int i = 1; i<=n; i++){
            result = result + (char)('a'+ rnd.nextInt(26));
        }
        return result;
    }

    public static String randomNumber(int n){
        var rnd = new Random();
        var result = "";
        for(int i = 1; i<=n; i++){
            result = result + rnd.nextInt(9);
        }
        return result;
    }

}
