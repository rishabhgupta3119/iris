package A2.BaseTest;


import org.example.Factory.DriverFactory;
import org.example.Pages.WebTablePage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log =
            Logger.getLogger(getClass().getName());


    @BeforeMethod
    @Parameters("browser")
    public void setup(
            @Optional("") String browser) {

        String browserName =
                browser.isEmpty()
                        ? ConfigReader.getProperty("browser")
                        : browser;

        log.info(
                "Launching browser: "
                        + browserName);

        driver =
                DriverFactory.initDriver(
                        browserName);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}