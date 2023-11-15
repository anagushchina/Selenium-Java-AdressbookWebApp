package ca.stqa.mantis.tests;

import ca.stqa.mantis.common.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationTest extends TestBase {

    @ParameterizedTest
    @MethodSource("users")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        appMan.jamesCli().addUser(email, password);
        appMan.web().signup(username, email);
        var messages = appMan.mail().receive(email, password, Duration.ofSeconds(10));
        String url = Utils.extractUrl(messages);
        appMan.web().finishSignup(url, password);
        appMan.http().login(username, password);
        Assertions.assertTrue(appMan.http().isLoggedIn());
    }

    public static List<String> users(){
        var users = new ArrayList<String>();
        users.add(Utils.randomString(5));
        users.add(Utils.randomString(7));
        users.add(Utils.randomString(3));
        return users;
    }
}
