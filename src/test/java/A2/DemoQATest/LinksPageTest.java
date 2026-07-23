package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.LinksPage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinksPageTest extends BaseTest {

    @Test
            public void doLinksPageTest(){
        LinksPage linksPage=new LinksPage(driver);
        linksPage.openUrl(ConfigReader.getProperty("base.url") + "/links");
        linksPage.doHomeLinkClick();
        linksPage.doValidateHomeLinkTab("demosite");

        String CreatedResponse =
                linksPage.getLinkResponse("Created");
        System.out.println(CreatedResponse);
        Assert.assertEquals(CreatedResponse, "Link has responded with staus 201 and status text Created");

        String NoContentResponse =
                linksPage.getLinkResponse("No Content");
        System.out.println(NoContentResponse);
        Assert.assertEquals(NoContentResponse, "Link has responded with staus 204 and status text No Content");

        String NotFoundResponse =
                linksPage.getLinkResponse("Not Found");
        System.out.println(NotFoundResponse);
        Assert.assertEquals(NotFoundResponse, "Link has responded with staus 404 and status text Not Found");


    }


}
