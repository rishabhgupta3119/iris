package org.example.Pages;

import org.example.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadAndDownloadPage extends BasePage {
    By downloadButton= By.xpath("//a[@id='downloadButton']");
    By uploadFile=By.xpath("//input[@id='uploadFile']");
    By uploadFilePath=By.xpath("//p[@id='uploadedFilePath']");

    public UploadAndDownloadPage(WebDriver driver){
        super(driver);
    }

    public void clickDownload(){
          doClick(downloadButton);
    }

    public String getDownloadFileName() {

        return getLocator(downloadButton)
                .getAttribute("download");
    }

    public void doUploadFile(String uploadFilePath){
        wait.doSendKeys(uploadFile, uploadFilePath);
    }

    public String getUploadFilePath(){
          return  getLocatorText(uploadFilePath);
    }


}
