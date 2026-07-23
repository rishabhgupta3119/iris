package org.example.Pages;

import org.example.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class LinksPage extends BasePage {

    By HomeLink=By.xpath("//a[@id='simpleLink']");
    By responseText = By.id("linkResponse");


    public LinksPage(WebDriver driver){
        super(driver);
    }

    public void doHomeLinkClick(){
        clickLink(HomeLink);
    }

    public void doValidateHomeLinkTab(String expectedTitle){
        clickLinkAndHandleTab(expectedTitle);
    }

    public String getLinkResponse(String linkName) {
        By linkLocator = By.xpath(
                "//div[@id='linkWrapper']//a[normalize-space()='"+ linkName + "']");
        return randomClickAndValidateResponseText(linkLocator, responseText);
    }



}
