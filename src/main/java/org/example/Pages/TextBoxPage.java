package org.example.Pages;

import org.example.BasePage.BasePage;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TextBoxPage extends BasePage {
//    private WebDriver driver;
    private By name = By.id("userName");
    private By email = By.id("userEmail");
    private By currentAddress = By.id("currentAddress");
    private By permanentAddress = By.id("permanentAddress");
    private By submit = By.id("submit");
    private By output= By.id("output");

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }
//    public void openUrl(String url){
//         driver.get(url);
//    }
    public void fillForm(String n, String e, String ca, String pa) {
        wait.doSendKeys(name, n);
        wait.doSendKeys(email, e);
        wait.doSendKeys(currentAddress, ca);
        wait.doSendKeys(permanentAddress, pa);
    }
    public void doClick() throws InterruptedException {
        wait.scrollToClick(submit);
    }

    public boolean isOutputVisible(){
        return wait.doScrollToWaitForVisibility(output);
    }

}