package A2.DemoQATest;
import A2.BaseTest.BaseTest;
import org.example.Pages.RadioButtonPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RadioButtonPageTest extends BaseTest {

    @Test
    public void testRadioButtonPageTest() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        RadioButtonPage page = new RadioButtonPage(driver);

        page.openUrl(ConfigReader.getProperty("base.url") + "/radio-button");

        // Step 1: Display validation
        if (page.isRadioButtonDisplay()) {
            System.out.println("Impressive radio button displayed");
        } else {
            System.out.println("Impressive radio button NOT displayed");
            softAssert.fail("Radio button not displayed");
            softAssert.assertAll();
            return;
        }

        //Step 2: Default selection check
        if (page.isRadioButtonSelected()) {
            System.out.println("Radio button is selected by default (ISSUE)");
            softAssert.fail("Default selection issue");
        } else {
            System.out.println("Radio button NOT selected by default");
        }

        // Step 3: Select radio
        page.selectRadioButton();

        // Step 4: Validate selection
        softAssert.assertTrue(page.isRadioButtonSelected(),
                "Radio button not selected after click");

        System.out.println("Radio button selected");

        softAssert.assertAll();
    }
}