package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper loginHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private JdbcHelper jdbcHelper;
    private HibernateHelper hbm;

    private Properties properties;

    public void initSession(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        var seleniumServer = properties.getProperty("seleniumServer");
        if (driver == null) {
            if (browser.equals("chrome")){
                if (seleniumServer!=null){
                    driver = new RemoteWebDriver(new URL(seleniumServer), new ChromeOptions());
                } else {
                driver = new ChromeDriver();}
            } else if (browser.equals("firefox")){
                if (seleniumServer!=null){
                    driver = new RemoteWebDriver(new URL(seleniumServer), new FirefoxOptions());
                } else {
                driver = new FirefoxDriver();}
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().setSize(new Dimension(1440, 823));
            initLoginHelper().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

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

    public ContactHelper initContactHelper() {
        if (contactHelper == null){
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }

    public JdbcHelper initJdbcHelper() {
        if (jdbcHelper == null){
            jdbcHelper = new JdbcHelper(this);
        }
        return jdbcHelper;
    }

    public HibernateHelper initHbm() {
        if (hbm == null){
            hbm = new HibernateHelper(this);
        }
        return hbm;
    }



    public boolean isElementPresent(By locator) {
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception){
            return false;
        }
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }


}
