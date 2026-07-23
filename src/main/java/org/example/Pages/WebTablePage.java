package org.example.Pages;

import org.example.BasePage.BasePage;
import org.example.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class WebTablePage extends BasePage {
    By addNewRecord = By.xpath("//button[@id='addNewRecordButton']");
    By addFirstName = By.xpath("//input[@id='firstName']");
    By addLastName = By.xpath("//input[@id='lastName']");
    By email = By.xpath("//input[@id='userEmail']");
    By age = By.xpath("//input[@id='age']");
    By salary = By.xpath("//input[@id='salary']");
    By department = By.xpath("//input[@id='department']");
    By submit = By.xpath("//button[@id='submit']");
    By lastAdded = By.xpath("//tbody/tr[last()]/td[1]");
    By lastEdit=By.xpath("//tbody/tr[last()]//span[@title='Edit']");
    By getLastEditSalaryText= By.xpath("//tbody/tr[last()]/td[5]");
    By lastDelete= By.xpath("//tbody/tr[last()]//span[@title='Delete']");


    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    public void openWebTablePageUrl(String url) {
        openUrl(url);
    }

    public void doClickAdd() throws InterruptedException {
        clickButton(addNewRecord);
    }

    public void addData(String fn, String ln, String e, String a, String sal, String depart) throws InterruptedException {
        wait.doSendKeys(addFirstName, fn);
        wait.doSendKeys(addLastName, ln);
        wait.doSendKeys(email, e);
        wait.doSendKeys(age, a);
        wait.doSendKeys(salary, sal);
        wait.doSendKeys(department, depart);
    }

    public void doClickSubmit() {
        clickSubmit(submit);
    }

    public String getLastAddedFirstName() {
         return getLocatorText(lastAdded);
  }

  public void editData(String sly) throws InterruptedException {
   clickButton(lastEdit);
   wait.doSendKeys(salary, sly);
   clickSubmit(submit);
      Thread.sleep(2000);
  }

  public String getLastEditSalary(){
        return getLocatorText(getLastEditSalaryText);
  }

  public void deleteData() throws InterruptedException {
        clickButton(lastDelete);
  }


}
