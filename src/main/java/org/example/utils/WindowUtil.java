package org.example.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WindowUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public WindowUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    //Wait for number of windows
    public void waitForNumberOfWindows(int expectedNumber) {
        wait.until(driver -> driver.getWindowHandles().size() == expectedNumber);
    }

    //Switch to new tab
    public String switchToNewTab() {

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                return parentWindow; // return parent for later use
            }
        }
        throw new RuntimeException("No new tab found");
    }

    //Switch to specific tab by index
    public void switchToTabByIndex(int index) {
        int i = 0;
        for (String window : driver.getWindowHandles()) {
            if (i == index) {
                driver.switchTo().window(window);
                return;
            }
            i++;
        }
        throw new RuntimeException("Tab index not found: " + index);
    }

    //Validate title
    public void validateTitle(String expectedTitle) {
        wait.until(driver -> driver.getTitle().equals(expectedTitle));

        String actual = driver.getTitle();
        if (!actual.equals(expectedTitle)) {
            throw new RuntimeException(
                    "Title mismatch! Expected: " + expectedTitle + " but got: " + actual
            );
        }

        System.out.println("Title validated: " + actual);
    }

    //Close current tab and switch back
    public void closeTabAndSwitchBack(String parentWindow) {
        driver.close();
        driver.switchTo().window(parentWindow);
        String parentWindowTitle=driver.getTitle();
        System.out.println("Back To Parent Window and Validate the title: "+ parentWindowTitle);
    }

//    // ✅ Close all tabs except parent
//    public void closeAllTabsExceptParent(String parentWindow) {
//        Set<String> windows = driver.getWindowHandles();
//
//        for (String window : windows) {
//            if (!window.equals(parentWindow)) {
//                driver.switchTo().window(window);
//                driver.close();
//            }
//        }
//        driver.switchTo().window(parentWindow);
//    }
}
