package stepdefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Factory.DriverFactory;
import org.example.Pages.TextBoxPage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TextBoxSteps {

    private TextBoxPage textBoxPage;

    @Given("User opens Text Box page")
    public void openTextBoxPage() {

        textBoxPage =
                new TextBoxPage(
                        DriverFactory.getDriver());

        textBoxPage.openUrl(
                ConfigReader.getProperty("base.url")
                        + "/text-box");
    }

    @When("User enters full name {string}")
    public void enterFullName(String fullName) {

        textBoxPage.enterName(fullName);
    }

    @When("User enters email {string}")
    public void enterEmail(String email) {

        textBoxPage.enterEmail(email);
    }

    @When("User enters current address {string}")
    public void enterCurrentAddress(
            String address) {

        textBoxPage.enterCurrentAddress(address);
    }

    @When("User enters permanent address {string}")
    public void enterPermanentAddress(
            String address) {

        textBoxPage.enterPermanentAddress(address);
    }

    @When("User clicks Submit button")
    public void clickSubmitButton() throws InterruptedException {

        textBoxPage.doClick();
    }

    @Then("User details output should be displayed successfully")
    public void validateOutput() {

        Assert.assertTrue(
                textBoxPage.isOutputVisible());
    }
}