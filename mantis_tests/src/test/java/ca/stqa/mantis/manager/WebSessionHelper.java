package ca.stqa.mantis.manager;

import org.openqa.selenium.By;

public class WebSessionHelper extends HelperBase{

    public WebSessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@type='submit']"));
    }


    public void signup(String username, String email) {
        click(By.xpath("//a[text()='Signup for a new account']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@type='submit']"));
//        click(By.cssSelector("a.btn-success"));
    }

    public void finishSignup(String url, String password){
        manager.driver().get(url);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }


    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
