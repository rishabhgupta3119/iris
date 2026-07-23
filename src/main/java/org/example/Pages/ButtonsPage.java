package org.example.Pages;

import org.example.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class ButtonsPage extends BasePage {
By doubleClickButton=By.xpath("//button[@id='doubleClickBtn']");
By doubleClickText=By.xpath("//p[@id='doubleClickMessage']");
By rightClickButton=By.xpath("//button[@id='rightClickBtn']");
By rightClickText=By.xpath("//p[@id='rightClickMessage']");
By clickButton=By.xpath("//button[text()='Click Me']");
By clickButtonText=By.xpath("//p[@id='dynamicClickMessage']");
    public ButtonsPage(WebDriver driver){
        super(driver);
    }

    public void doDoubleClick() {
      doubleClick(doubleClickButton);
    }

    public String getDoubleClickText(){
        return getLocatorText(doubleClickText);

    }

    public void  doRightClick(){
     rightClick(rightClickButton);
    }

    public String getRightClickText(){
        return getLocatorText(rightClickText);

    }

    public void doClick(){
       click(clickButton);
    }

    public String getClickText(){
        return getLocatorText(clickButtonText);

    }

}
