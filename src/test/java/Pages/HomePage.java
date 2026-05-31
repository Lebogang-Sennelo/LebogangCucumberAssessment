package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='Login']")
    WebElement loginButton_xpath;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(visibilityOf(loginButton_xpath));
        loginButton_xpath.click();
    }


}
