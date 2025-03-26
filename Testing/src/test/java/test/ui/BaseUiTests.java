package test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.lang.Thread.sleep;

public class BaseUiTests {
    WebDriver driver;
    Duration duration = Duration.of(30, ChronoUnit.SECONDS);

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver(options());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    public ChromeOptions options() {
        // Setting global timers
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setImplicitWaitTimeout(duration);
        chromeOptions.setPageLoadTimeout(duration);

        //Vtb self signed certs
        chromeOptions.setAcceptInsecureCerts(true);

        //Just looks better
        chromeOptions.addArguments("--start-maximized");
        return chromeOptions;
    }

    @Test
    public void testCase() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
