package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.UploadAndDownloadPage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UploadAndDownLoadPageTest extends BaseTest {


    @Test
    public void validateDownloadedFile() {

        UploadAndDownloadPage page = new UploadAndDownloadPage(driver);

        page.openUrl(ConfigReader.getProperty("base.url") + "/upload-download");

//        String downloadDirectory = System.getProperty("user.home")
//                        + File.separator
//                        + "Downloads";
        String downloadPath =
                ConfigReader.getProperty("download.path");

        String fileName = page.getDownloadFileName();

        // Delete old file
        //page.deleteFileIfExists(downloadDirectory, fileName);
        page.deleteFileIfExists(
                downloadPath,
                fileName);

        // Download file
        page.clickDownload();

        // Validate new download
//        boolean isDownloaded =
//                page.waitForDownload(
//                        downloadDirectory,
//                        fileName,
//                        15);
        boolean isDownloaded =
                page.waitForDownload(
                        downloadPath,
                        fileName,
                        15);

        Assert.assertTrue(
                isDownloaded,
                "Downloaded file not found");

        System.out.println(
                "✅ File downloaded successfully : "
                        + fileName);
    }


    @Test
    public void validateFileUpload() {

        UploadAndDownloadPage page =
                new UploadAndDownloadPage(driver);

        page.openUrl(ConfigReader.getProperty("base.url") + "/upload-download");

//        String filePath =
//                System.getProperty("user.dir")
//                        + File.separator
//                        + "TestData"
//                        + File.separator
//                        + "testfile1.txt";
        String filePath =
                System.getProperty("user.dir")
                        + File.separator
                        + ConfigReader.getProperty("testdata.path")
                        + File.separator
                        + "testfile1.txt";

        page.doUploadFile(filePath);

        File file = new File(filePath);

        String expectedFileName = file.getName();

        String actualUploadFilePath =
                page.getUploadFilePath();

        System.out.println(
                "Actual Uploaded Path : "
                        + actualUploadFilePath);

        Assert.assertTrue(
                actualUploadFilePath.contains(expectedFileName),
                "Uploaded file validation failed");

        System.out.println(
                "✅ File uploaded successfully : "
                        + expectedFileName);
    }
}
