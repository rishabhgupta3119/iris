package A1;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class DoubleClickTest extends BaseTest {

    @Test
    public void testDoubleClickAlert() {

        driver.get("http://only-testing-blog.blogspot.com/2014/09/selectable.html");

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'Double-Click Me To See Alert')]")
                )
        );

        Actions actions = new Actions(driver);
        actions.doubleClick(button).perform();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }
}