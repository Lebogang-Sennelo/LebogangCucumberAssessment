package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SignUpPage {

    WebDriver driver;

    // "Create Your Account" heading visible in the screenshot
    @FindBy(xpath = "//h2[contains(text(),'Create Your Account')] | //h1[contains(text(),'Create Your Account')]")
    WebElement signUpHeading;

    @FindBy(id = "register-group")
    WebElement groupDropdown;

    @FindBy(xpath = "//select[@id='register-group']/option")
    List<WebElement> groupDropdownOptions;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifySignUpPageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(visibilityOf(signUpHeading));
        Assert.assertTrue(signUpHeading.isDisplayed(), "Sign-up page is not displayed");
    }

    public void verifyGroupExistsInDropdown(String groupName) {
        // Wait for the dropdown to be visible
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(groupDropdown));

        // Check that at least one option matches the expected group name
        boolean groupFound = groupDropdownOptions
                .stream()
                .anyMatch(option -> option.getText().trim().equalsIgnoreCase(groupName.trim()));

        Assert.assertTrue(groupFound,
                "Group '" + groupName + "' was NOT found in the sign-up group dropdown");
        System.out.println("✅ Group '" + groupName + "' successfully validated in sign-up dropdown");
    }
}
