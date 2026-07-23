package org.example.Pages;

import org.example.BasePage.BasePage;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;
import org.openqa.selenium.*;

import java.util.logging.Logger;

public class RadioButtonPage extends BasePage {

    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }

    By impressiveRadio = By.id("impressiveRadio");

    public boolean isRadioButtonDisplay(){
         return isDisplayed(impressiveRadio);
    }

    //Check selected
    public boolean isRadioButtonSelected() {
        return isSelected(impressiveRadio);
    }

    //Click
    public void selectRadioButton() throws InterruptedException {
    selectRadio(impressiveRadio);
    }
}
