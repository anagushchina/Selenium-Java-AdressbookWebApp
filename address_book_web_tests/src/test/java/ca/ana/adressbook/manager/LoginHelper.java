package ca.ana.adressbook.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{


    public LoginHelper(ApplicationManager manager){
        super(manager);
    }
    void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.cssSelector("input[type=submit]"));
    }
}
