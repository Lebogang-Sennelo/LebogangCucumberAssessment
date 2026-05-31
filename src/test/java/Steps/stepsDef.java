package Steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class stepsDef extends Base {


    @Given("i am on the login page")
    public void i_am_on_the_login_page() {
        homePage.clickLoginButton();
        loginPage.verifyLoginPageIsDisplayed();
    }

    @And("I enter email (.*)$")
    public void i_enter_email(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter password (.*)$")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click login button")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("i should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        dashboardPage.verifyDashboardPageIsDisplayed();
    }

    // Click user menu then logout
    @And("I logout from the application")
    public void iLogoutFromTheApplication() {
        dashboardPage.clickLogout();
    }

    // After logout we land on login page — click "Sign Up Here"
    @And("I navigate to sign up page")
    public void iNavigateToSignUpPage() {
        homePage.clickLoginButton();
        loginPage.verifyLoginPageIsDisplayed();
        loginPage.clickSignUpHereLink();
        signUpPage.verifySignUpPageIsDisplayed();
    }

    // Assert the created group appears in the dropdown
    @Then("I validate that (.*) exists in the group dropdown")
    public void iValidateThatGroupExistsInDropdown(String groupName) {
        signUpPage.verifyGroupExistsInDropdown(groupName);
    }


    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @And("I click on the logged in user")
    public void iClickOnTheLoggedInUser() {
        dashboardPage.clickUserMenuButton_xpath();

    }

    @And("I click on the admin panel")
    public void iClickOnTheAdminPanel() {
        dashboardPage.clickAdminPanelButton_xpath();

    }

    @And("I click on the groups tab")
    public void iClickOnTheGroupsTab() {

        dashboardPage.clickGroupsButton();
    }

    @And("I click on the create group button")
    public void iClickOnTheCreateGroupButton() {
        dashboardPage.clickCreateNewGroup();
    }

    @And("I enter group name (.*)$")
    public void iEnterGroupNameGroupName(String groupName) throws InterruptedException {
        dashboardPage.groupNameInput(groupName);
    }

    @And("I enter group description (.*)$")
    public void iEnterGroupDescriptionGroupDescription(String groupDescription) throws InterruptedException {
        dashboardPage.groupDescriptionArea(groupDescription);
    }

    @And("I enter year (.*)$")
    public void iEnterYear(String year) throws InterruptedException {
        dashboardPage.enterGroupYear(year);
    }

    @And("I enter max capacity (.*)$")
    public void iEnterMaxCapacityMaxCapacity(String maxCapacity) {
        dashboardPage.enterMaxCapacity(maxCapacity);
    }

    @And("I enter start date (.*)$")
    public void iEnterStartDateStartDate(String startDate) {
        dashboardPage.enterStartDate(startDate);
    }

    @And("I enter end date (.*)$")
    public void iEnterEndDateEndDate(String endDate) {
        dashboardPage.enterEndDate(endDate);
    }

    @When("I click on the create group submit button")
    public void iClickOnTheSubmitCreateGroupButton() {
        dashboardPage.clickCreateGroupSubmitButton();
    }

    @Then("i should see the group created successfully")
    public void iShouldSeeTheGroupCreatedSuccessfully() {
        dashboardPage.verifyGroupCreatedSuccessfully();
    }

    @AfterStep
    public void addScreenshots(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshots, "image/png", "image");
        }
    }


}
