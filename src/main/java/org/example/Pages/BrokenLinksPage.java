package org.example.Pages;

import org.example.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrokenLinksPage extends BasePage {

    By validImage = By.xpath("//p[text()='Valid image']/following-sibling::img[1]");
    By brokenImage = By.xpath("//p[text()='Broken image']/following-sibling::img[1]");

    By validLink = By.linkText("Click Here for Valid Link");
    By brokenLink = By.linkText("Click Here for Broken Link");


    public BrokenLinksPage(WebDriver driver){
        super(driver);
    }

    public boolean getValidImageRenderStatus() {
        return isImageRendered(validImage);
    }

    public boolean getBrokenImageRenderStatus() {
        return isImageRendered(brokenImage);
    }


    public int getValidLinkResponseCode() {
      return   getLinkResponseCode(validLink);
    }
    public int getBrokenLinkResponseCode() {
        return   getLinkResponseCode(brokenLink);
    }




}
