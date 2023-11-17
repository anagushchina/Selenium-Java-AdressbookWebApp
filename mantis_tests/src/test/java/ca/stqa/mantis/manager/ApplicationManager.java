package ca.stqa.mantis.manager;

import ca.stqa.mantis.model.DeveloperMailUser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private WebSessionHelper webSessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private JamesApiHelper jamesApiHelper;
    private MailHelper mailHelper;
    private DeveloperMailHelper developerMailHelper;


    public void initSession(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver(){
        if (driver == null) {
            if (browser.equals("chrome")){
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")){
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().setSize(new Dimension(1440, 823));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return driver;
    }

    public WebSessionHelper web(){
        if(webSessionHelper == null) {
            webSessionHelper = new WebSessionHelper(this);
        }
        return webSessionHelper;
    }

    public HttpSessionHelper http(){
        if(httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli(){
        if(jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public JamesApiHelper jamesApi(){
        if(jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public MailHelper mail(){
        if(mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DeveloperMailHelper developerMail(){
        if(developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public String property(String propertyName){
        return properties.getProperty(propertyName);
    }


}
