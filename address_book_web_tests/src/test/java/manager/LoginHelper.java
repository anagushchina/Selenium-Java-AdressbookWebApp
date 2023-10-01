package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager){
        this.manager = manager;
    }

    void login(String username, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(username);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.cssSelector("input[type=submit]")).click();
    }
}
