package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtil {
    protected WebDriverWait wait;
    protected WebDriver driver;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("explicit.wait"))));
    }

    // Wait for visibility
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for clickability
    public WebElement waitForClickable(By locator) {
         return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for presence
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for text
    public boolean waitForText(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }


    // Send keys
    public void doSendKeys(By locator, String value) {
        waitForVisibility(locator).sendKeys(value);
    }

    public void scrollToClick(By locator) throws InterruptedException {

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

// Step 3: Wait a bit for UI stabilization
        Thread.sleep(500);

// Step 4: Try normal click
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Normal click failed. Using JS click.");

            // Step 5: Fallback to JS click
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }

    }

    public boolean doScrollToWaitForVisibility(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        // Scroll into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        return element.isDisplayed();
    }

    public boolean waitForElementToBeEnabled(By locator) {

        try {

            wait.until(driver ->
                    driver.findElement(locator).isEnabled());

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean waitForElementVisible(By locator) {

        try {

            wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(locator));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean waitForAttributeChange(
            By locator,
            String attribute,
            String initialValue) {

        try {

            wait.until(driver ->
                    !driver.findElement(locator)
                            .getAttribute(attribute)
                            .equals(initialValue));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

}