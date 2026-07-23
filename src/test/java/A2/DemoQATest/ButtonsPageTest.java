package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.ButtonsPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

public class ButtonsPageTest extends BaseTest {

    @Test
    public void doDoubleClickTest() {
        ButtonsPage buttonsPage= new ButtonsPage(driver);
        buttonsPage.openUrl(ConfigReader.getProperty("base.url") + "/buttons");
        buttonsPage.doDoubleClick();
        String doubleText=buttonsPage.getDoubleClickText();
        System.out.println(doubleText);
        buttonsPage.doRightClick();
        String rightClick=buttonsPage.getRightClickText();
        System.out.println(rightClick);
        buttonsPage.doClick();
        String clickText=buttonsPage.getClickText();
        System.out.println(clickText);
    }
}
