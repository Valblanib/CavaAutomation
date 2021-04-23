import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public String browserStackUserName = "";
    public String browserStackAccessKey = " ";
    public String sauceLabsUserName = " ";
    public String sauceLabsAccessKey = " ";

    public static WebDriver driver = null;

    @Parameters({"useCloudEnv", "cloudEnvName", "url", "os", "os_version", "browserName", "browserVersion"})

    @BeforeMethod
    public void setUp(String useCloudEnv, String cloudEnvName, String url, String os, String os_version, String browserName, String browserVersion) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver(String OS, String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", " ../Generic/Drivers/chromedriver");
                driver = new ChromeDriver();
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", " ../Generic/Drivers/chromedriver.exe");
            }
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", " ../Generic/Drivers/geckodriver");
                driver = new FirefoxDriver();
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", " ../Generic/Drivers/geckodriver.exe");
            }
        }
        return driver;
    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
    }
    public static void navigateBack(){
        driver.navigate().back();
    }
    public static void navigateFoward(){
        driver.navigate().forward();
    }
    public void  clearImput(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }
    public List<WebElement> getListOfWebElements(String Locator){
        List<WebElement> elementList = (List<WebElement>) driver.findElement(By.cssSelector(Locator));
        return elementList;
    }
}
