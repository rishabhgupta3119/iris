package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.TextBoxPage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TextBoxTest extends BaseTest {

    @Test
    public void fullFlow() throws InterruptedException {

        TextBoxPage textPage = new TextBoxPage(driver);

        textPage.openUrl(ConfigReader.getProperty("base.url") + "/text-box");
        textPage.fillForm("Rishabh", "test@test.com", "Delhi", "India");
        textPage.doClick();
        if (textPage.isOutputVisible()) {
            System.out.println("Output section is visible");
        } else {
            Assert.fail("Output section is NOT visible");
        }

    }
}