package hooks;

import org.example.Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp() {

        DriverFactory.initDriver(
                ConfigReader.getProperty(
                        "browser"));
    }

    @After
    public void tearDown() {

        DriverFactory.getDriver()
                .quit();
    }
}