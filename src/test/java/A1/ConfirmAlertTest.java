package A1;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class ConfirmAlertTest extends BaseTest {

    @Test
    public void testConfirmAlertFlow() {

        driver.get("https://www.jquery-az.com/javascript/demo.php?ex=151.1_1");

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Click here to show confirm alert']")
                )
        );

        button.click();

        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();

        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    }
}