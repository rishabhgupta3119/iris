package org.example.Pages;


import org.example.BasePage.BasePage;
import org.example.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.*;
import java.util.logging.Logger;

public class CheckBoxPage extends BasePage {

    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }
    //XPATH BUILDERS (IMPORTANT)
    // Expand arrow
    private By getExpandXpath(String label) {
        return By.xpath(
                "//span[normalize-space()='" + label + "']" +
                        "/ancestor::span[contains(@class,'rc-tree-node-content-wrapper')]" +
                        "/preceding-sibling::span[contains(@class,'rc-tree-switcher')]"
        );
    }
    //Expand Node
    public void expandNode(String label) {
        try {
            WebElement element = wait.waitForVisibility(getExpandXpath(label));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", element);

            if (element.getAttribute("class").contains("close")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                log.info("Expanded: " + label);
            }

        } catch (Exception e) {
            log.warning("Expand failed: " + label);
        }
    }

    public void selectCheckbox(String label) {

        try {

            // Correct locator for checkbox (NOT label)
            By checkboxLocator = By.xpath(
                    "//span[text()='" + label + "']" +
                            "/ancestor::span[contains(@class,'rc-tree-node-content-wrapper')]" +
                            "/preceding-sibling::span[@role='checkbox']"
            );

            List<WebElement> elements = driver.findElements(checkboxLocator);

            if (elements.isEmpty()) {
                log.warning("Checkbox NOT FOUND: " + label);
                return;
            }

            WebElement checkbox = elements.get(0);

            // Scroll
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", checkbox);

            // SIMPLE click (works best here)
            checkbox.click();

            log.info("Checkbox Selected: " + label);

        } catch (Exception e) {
            log.severe("Failed: " + label + " -> " + e.getMessage());
        }
    }

    public List<String> getAllCheckBoxLabels() {
        List<String> labels = new ArrayList<>();

        try {
            List<WebElement> elements = driver.findElements(
                    By.xpath("//span[@class='rc-tree-title']")
            );

            for (WebElement el : elements) {
                String text = el.getText().trim();
                if (!text.isEmpty()) {
                    labels.add(text);
                }
            }

            log.info("Available Checkboxes: " + labels);

        } catch (Exception e) {
            log.warning("Unable to fetch checkbox list: " + e.getMessage());
        }

        return labels;
    }

    public void validateAndSelectCheckboxes(List<String> requiredItems) {

        List<String> availableItems = getAllCheckBoxLabels();

        for (String item : requiredItems) {

            if (availableItems.contains(item)) {

                log.info("Found: " + item + " → Selecting...");
                expandNode(item);
                selectCheckbox(item);// ensure visible

            } else {
                log.warning("NOT FOUND: " + item);
            }
        }
    }

    
}