package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.DynamicPropertiesPage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicPropertiesPageTest extends BaseTest {

    @Test
public void validateDynamicProperties() throws InterruptedException {
        DynamicPropertiesPage page = new DynamicPropertiesPage(driver);
        page.openUrl(ConfigReader.getProperty("base.url") + "/dynamic-properties");

// Capture BEFORE color change
        String beforeClass = page.getColorButtonClass();

        System.out.println("Before Class : " + beforeClass);

// Wait 6 seconds
        Thread.sleep(6000);

// Capture AFTER color change
        String afterClass = page.getColorButtonClass();

        System.out.println("After Class : " + afterClass);

        Assert.assertNotEquals(
                beforeClass,
                afterClass,
                "Color did not change"
        );

        System.out.println("✅ Color changed successfully");

// Now check enable button
        Assert.assertTrue(
                page.isEnableButtonWorking()
        );

        System.out.println("✅ Enable button working");

// Now check visible button
        Assert.assertTrue(
                page.isVisibleButtonDisplayed()
        );

        System.out.println("✅ Visible After button displayed");


    }
}
