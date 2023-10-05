package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper loginHelper;
    private GroupHelper groupHelper;

    public LoginHelper initLoginHelper(){
        if(loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public GroupHelper initGroupHelper(){
        if(groupHelper == null) {
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }

    public void initSession(String browser) {
        if (driver == null) {
            if (browser.equals("chrome")){
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")){
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/index.php");
            driver.manage().window().setSize(new Dimension(1440, 823));
            initLoginHelper().login("admin", "secret");
        }
    }

    public boolean isElementPresent(By locator) {
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception){
            return false;
        }
    }

}
