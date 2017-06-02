package miksrok.testing;

import miksrok.testing.pages.MainPage;
import miksrok.testing.util.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public abstract class BaseScript {

    private final String CHROME_PATH = "src\\test\\resources\\chromedriver.exe";
    private final String FIREFOX_PATH = "src\\test\\resources\\geckodriver.exe";

    private EventFiringWebDriver driver;
    protected MainPage mainPage;


    private WebDriver getDriver(String browser) {
        switch (browser){
            case "chrome":{
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                return new ChromeDriver();
            }
            default:{
                System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);
                return new FirefoxDriver();
            }
        }
    }

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        driver = new EventFiringWebDriver(getDriver(browser));
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
    }

   /* @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

}
