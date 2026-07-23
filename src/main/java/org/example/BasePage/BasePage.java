package org.example.BasePage;

import org.example.Pages.WebTablePage;
import org.example.utils.WaitUtil;
import org.example.utils.WindowUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.io.File;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtil wait;
    Actions action;
    WindowUtil windowUtil;
    protected  Logger log = Logger.getLogger(BasePage.class.getName());


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WaitUtil(driver);
        this.action=new Actions(driver);
        this.windowUtil = new WindowUtil(driver);
    }

    public void openUrl(String url){
         driver.get(url);
    }

    public WebElement getLocator(By locator){
        return driver.findElement(locator);
    }
    public String getLocatorText(By locator){
        return getLocator(locator).getText();
    }

    //Check visible
    public boolean isDisplayed(By locator) {
        return getLocator(locator).isDisplayed();
    }

    //Check selected
    public boolean isSelected(By locator) {
        return getLocator(locator).isSelected();
    }

    //click on Radio button
    public void selectRadio(By locator) throws InterruptedException {
        wait.waitForClickable(locator).click();
        Thread.sleep(2000);
    }

    //click on button with Thread Sleep
    public void clickButton(By locator) throws InterruptedException {
        wait.waitForClickable(locator).click();
        Thread.sleep(2000);
    }

    public void clickSubmit(By locator){
        wait.waitForClickable(locator).click();
    }
    //click on button with wait util
    public void doClick(By locator){
        wait.waitForClickable(locator).click();
    }


    public void doubleClick(By locator) {
        WebElement doubleClickElement = wait.waitForClickable(locator);
        action.moveToElement(doubleClickElement)
                .pause(Duration.ofMillis(2000))
                .doubleClick(doubleClickElement)
                .perform();
    }

    public void rightClick(By locator){
    WebElement rightClickElement=wait.waitForClickable(locator);
    action.moveToElement(rightClickElement)
            .pause(Duration.ofMillis(2000))
            .contextClick(rightClickElement)
            .perform();
    }

    public void click(By locator){
        WebElement clickElement=wait.waitForClickable(locator);
        action.moveToElement(clickElement)
                .pause(Duration.ofMillis(2000))
                .click(clickElement)
                .perform();
    }

    public boolean isRecordPresent(String firstName, String lastName,String age,
                                   String email, String salary, String department) {

        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            log.info("🔍 Total rows found: " + rows.size());

            if (cols.size() > 0) {

                String fName = cols.get(0).getText();
                String lName = cols.get(1).getText();
                String ageVal =cols.get(2).getText();
                String emailVal = cols.get(3).getText();
                String salaryVal = cols.get(4).getText();
                String deptVal = cols.get(5).getText();
                log.info("➡ Checking Row: " + fName + " | " + lName + " | " + emailVal);
                if (fName.equals(firstName) &&
                        lName.equals(lastName) &&
                        ageVal.equals(age) &&
                        emailVal.equals(email) &&
                        salaryVal.equals(salary) &&
                        deptVal.equals(department)) {

                    log.info("Record FOUND in table");
                    return true;


                }
            }
        }

        log.warning("Record NOT found in table");
        return false;


    }

    public void clickLink(By locator){
        WebElement link=wait.waitForClickable(locator);
        link.click();
    }

    public void clickLinkAndHandleTab(String expectedTitle) {

        String parentWindow = driver.getWindowHandle();
//        // Click link
//        wait.waitForClickable(locator).click();

        // Wait for new tab
        windowUtil.waitForNumberOfWindows(2);

        // Switch to new tab
        windowUtil.switchToNewTab();

        // Validate title
        windowUtil.validateTitle(expectedTitle);

        // Close new tab & switch back
        windowUtil.closeTabAndSwitchBack(parentWindow);
    }

//    public String randomClickAndValidateResponseText(By locator, By responseLocator){
//        clickLink(locator);
//        WebElement responseElement = wait.waitForVisibility(responseLocator);
//        return responseElement.getText();
//        //System.out.println("Response: " + responseText);
//
//    }

    public String randomClickAndValidateResponseText(By linkLocator, By responseLocator) {

        String previousText = "";

        try {
            previousText = getLocatorText(responseLocator);
        } catch (Exception e) {
            // Ignore first execution
        }

        WebElement link = wait.waitForClickable(linkLocator);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        link);

        link.click();

        String oldText = previousText;

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> {

                    String currentText =
                            driver.findElement(responseLocator).getText();

                    return !currentText.isBlank()
                            && !currentText.equals(oldText);
                });

        return driver.findElement(responseLocator).getText();
    }
    public boolean isImageRendered(By locator) {

        WebElement image = wait.waitForVisibility(locator);

        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                        image);
    }


    public int getLinkResponseCode(By locator) {

        try {

            String url = driver.findElement(locator)
                    .getAttribute("href");

            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            return connection.getResponseCode();

        } catch (Exception e) {

            log.severe("Failed to validate link : " + e.getMessage());
            return -1;
        }
    }

    public void deleteFileIfExists(
            String downloadDirectory,
            String fileName) {

        File file = new File(
                downloadDirectory
                        + File.separator
                        + fileName);

        if (file.exists()) {

            if (file.delete()) {
                log.info("✅ Existing file deleted: "
                        + fileName);
            } else {
                log.warning("❌ Unable to delete file: "
                        + fileName);
            }
        }
    }



    public boolean waitForDownload(
            String downloadDirectory,
            String fileName,
            int timeoutSeconds) {

        File file = new File(
                downloadDirectory
                        + File.separator
                        + fileName);

        for (int i = 0; i < timeoutSeconds; i++) {

            if (file.exists()) {

                log.info(
                        "✅ Downloaded file found: "
                                + file.getAbsolutePath());

                return true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.warning(
                "❌ Downloaded file not found: "
                        + fileName);

        return false;
    }



}
