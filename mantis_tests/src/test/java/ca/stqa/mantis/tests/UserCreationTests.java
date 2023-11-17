package ca.stqa.mantis.tests;

import ca.stqa.mantis.common.Utils;
import ca.stqa.mantis.model.DeveloperMailUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserCreationTests extends TestBase{
    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        var password = "password";
        user= appMan.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        appMan.web().signup(user.name(), email);

        var message = appMan.developerMail().receive(user, Duration.ofSeconds(20));
        var url = Utils.extractUrl(message);

        appMan.web().finishSignup(url, password);

        appMan.http().login(user.name(), password);
        Assertions.assertTrue(appMan.http().isLoggedIn());
    }

//    @AfterEach
//    void deleteUser(){
//        appMan.developerMail().deleteUser(user);
//    }



    //using local mail server
    @ParameterizedTest
    @MethodSource("users")
    void canCreateUserWithLocalEmailServer(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        appMan.jamesApi().addUser(email, password);

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
//        users.add(Utils.randomString(7));
//        users.add(Utils.randomString(3));
        return users;
    }
}
