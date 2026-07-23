package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.BrokenLinksPage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenLinksPageTest extends BaseTest {
    @Test
    public void validateBrokenLinksAndImages() {
        BrokenLinksPage page = new BrokenLinksPage(driver);
        page.openUrl(ConfigReader.getProperty("base.url") + "/broken");

        boolean validImageStatus = page.getValidImageRenderStatus();

        if (!validImageStatus) {
            System.out.println("Valid image is not rendered");
        } else {
            System.out.println("Valid image is rendered");
        }
        Assert.assertFalse(validImageStatus);
        Assert.assertFalse(
                page.getBrokenImageRenderStatus(),
                "Broken image should not be rendered"
        );

        boolean brokenImageStatus = page.getBrokenImageRenderStatus();

        if (!brokenImageStatus) {
            System.out.println("Broken image is not rendered");
        } else {
            System.out.println("Broken image is rendered");
        }

        Assert.assertFalse(brokenImageStatus);


        // Valid Link
        Assert.assertEquals(
                page.getValidLinkResponseCode(),
                301,
                "Valid link is broken"
        );

        System.out.println("Valid link is working");

        // Broken Link
        Assert.assertEquals(
                page.getBrokenLinkResponseCode(),
                403,
                "Broken link not detected"
        );

        System.out.println("Broken link detected");
    }
//@Test
//public void validateBrokenLinksAndImages() {
//
//    BrokenLinksPage page =
//            new BrokenLinksPage(driver);
//
//    page.openUrl("https://demoqa.com/broken");
//
//    boolean validImageStatus =
//            page.getValidImageRenderStatus();
//
//    Assert.assertFalse(
//            validImageStatus,
//            "Valid image should not be rendered"
//    );
//
//    log.info(
//            "Valid Image Render Status : "
//                    + validImageStatus);
//
//    boolean brokenImageStatus =
//            page.getBrokenImageRenderStatus();
//
//    Assert.assertFalse(
//            brokenImageStatus,
//            "Broken image should not be rendered"
//    );
//
//    log.info(
//            "Broken Image Render Status : "
//                    + brokenImageStatus);
//
//    int validLinkCode =
//            page.getValidLinkResponseCode();
//
//    Assert.assertEquals(
//            validLinkCode,
//            301,
//            "Valid link response code mismatch"
//    );
//
//    log.info(
//            "Valid Link Response Code : "
//                    + validLinkCode);
//
//    int brokenLinkCode =
//            page.getBrokenLinkResponseCode();
//
//    Assert.assertEquals(
//            brokenLinkCode,
//            403,
//            "Broken link response code mismatch"
//    );
//
//    log.info(
//            "Broken Link Response Code : "
//                    + brokenLinkCode);
//}
    }

