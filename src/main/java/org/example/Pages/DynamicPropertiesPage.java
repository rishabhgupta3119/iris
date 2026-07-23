package org.example.Pages;

import org.example.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicPropertiesPage extends BasePage {
    By enableAfterBtn= By.xpath("//button[@id='enableAfter']");
    By colorChangeBtn=By.xpath("//button[@id='colorChange']");
    By visibleAfterBtn=By.xpath("//button[@id='visibleAfter']");


    public  DynamicPropertiesPage(WebDriver driver){
        super(driver);
    }
    public boolean isEnableButtonWorking() {
        return wait.waitForElementToBeEnabled(enableAfterBtn);
    }

    public String getColorButtonClass() {
        return getLocator(colorChangeBtn)
                .getAttribute("class");
    }

    public boolean isColorChanged() {

        String initialClass =
                getColorButtonClass();

        return wait.waitForAttributeChange(
                colorChangeBtn,
                "class",
                initialClass);
    }



    public boolean isVisibleButtonDisplayed() {

        return wait.waitForElementVisible(visibleAfterBtn);
    }

}
