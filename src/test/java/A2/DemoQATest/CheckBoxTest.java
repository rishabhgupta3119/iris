package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.CheckBoxPage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Arrays;

public class CheckBoxTest extends BaseTest {

    @Test
    public void testCheckboxSelection() {

        CheckBoxPage checkboxpage= new CheckBoxPage(driver);
        checkboxpage.openUrl(ConfigReader.getProperty("base.url") + "/checkbox");
        //Expand Tree Structure
        checkboxpage.expandNode("Home");
        checkboxpage.expandNode("Desktop");
        checkboxpage.expandNode("Documents");
        checkboxpage.expandNode("WorkSpace");
        checkboxpage.expandNode("Office");
        checkboxpage.expandNode("Downloads");
        checkboxpage.validateAndSelectCheckboxes(Arrays.asList(
                "React",
                "Angular",
                "Private",
                "Excel File.doc",
                "InvalidItem"   // This will show NOT FOUND
        ));
    }

    }






