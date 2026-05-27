package Steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class stepsDef {

    WebDriver driver;

    @Given("i am on the login page")
    public void i_am_on_the_login_page() {

        driver = new ChromeDriver();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[text()='Login']")).click();

    }

    @And("I enter email (.*)$")
    public void i_enter_email(String email) {
        driver.findElement(By.id("login-email")).sendKeys(email);
    }

    @And("I enter password (.*)$")
    public void i_enter_password(String password) {
        driver.findElement(By.id("login-password")).sendKeys(password);
    }

    @When("I click login button")
    public void i_click_login_button() {
        driver.findElement(By.id("login-submit")).click();
    }

    @Then("i should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        assert driver.findElement(By.xpath("//h2[contains(.,'Welcome back')]")).isDisplayed();
    }

    @And("I click mobile automation")
    public void iClickMobileAutomation() {
        //fffffff
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
