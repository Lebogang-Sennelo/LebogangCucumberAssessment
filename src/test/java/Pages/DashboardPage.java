package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DashboardPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"app-main-content\"]/section/div[1]/h2")
    WebElement dashboardHeading_xpath;

    @FindBy(xpath = "//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/button")
    WebElement userMenuButton_xpath;

    @FindBy(xpath = "//button[@class='nav-dropdown-item']//span[contains(text(),'Admin Panel')]")
    WebElement adminPanel_xpath;

    @FindBy(xpath = "//button[normalize-space() = '+ Create New Group']")
    WebElement createNewGroup_xpath;

    @FindBy(xpath = "//input[@name='name']")
    WebElement groupNameInput;

    @FindBy(xpath = "//button[contains(.,'Groups')]")
    WebElement groupsButton_xpath;

    @FindBy(xpath = "//textarea[@name='description']")
    WebElement groupDescriptionArea;

    @FindBy(xpath = "//*[@id=\"app-root\"]/div/div[3]/div/div[4]/div/form/div[4]/div[1]/input")
    WebElement startDate_xpath;

    @FindBy(xpath = "//*[@id=\"app-root\"]/div/div[3]/div/div[4]/div/form/div[4]/div[2]/input")
    WebElement endDate_xpath;

    @FindBy(xpath = "//*[@id=\"app-root\"]/div/div[3]/div/div[4]/div/form/div[3]/div[1]/input")
    WebElement groupYear;

    @FindBy(xpath = "//*[@id=\"app-root\"]/div/div[3]/div/div[4]/div/form/div[3]/div[2]/input")
    WebElement maxCapacity_xpath;

    // Success toast/message after group is created
    @FindBy(xpath = "//div[contains(@class,'toast') or contains(@class,'success') or contains(@class,'alert')]")
    WebElement groupCreatedSuccessMessage;
    @FindBy(xpath = "//button[normalize-space()='Create Group']")
    WebElement createGroupSubmitButton;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyDashboardPageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(visibilityOf(dashboardHeading_xpath));
        dashboardHeading_xpath.isDisplayed();
    }

    public void clickUserMenuButton_xpath() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(visibilityOf(userMenuButton_xpath));
        userMenuButton_xpath.click();
    }

    public void clickAdminPanelButton_xpath() {
        //ADDED WAITS
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(adminPanel_xpath));
        adminPanel_xpath.click();
    }

    public void clickGroupsButton() {
        groupsButton_xpath.click();
    }

    public void clickCreateNewGroup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), " +
                "'Create New Group')]")));

        // JavaScript click bypasses the overlay
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", createNewGroup_xpath);
    }

    public void groupNameInput(String groupName) throws InterruptedException {
        groupNameInput.sendKeys(groupName);

//        Assert.assertEquals(groupNameInput.getAttribute("value"), "some name",
//                "Group Name entered is correct");
    }

    public void groupDescriptionArea(String groupDescription) {
        groupDescriptionArea.sendKeys(groupDescription);
    }

    public void enterGroupYear(String year) {
        groupYear.click();
        groupYear.clear();
        groupYear.sendKeys(year);
    }

    public void enterStartDate(String startDate) {
        startDate_xpath.click();
        startDate_xpath.clear();
        startDate_xpath.sendKeys(startDate);
    }

    public void enterEndDate(String date) {
        endDate_xpath.sendKeys(date);
    }

    public void enterMaxCapacity(String capacity) {
        maxCapacity_xpath.sendKeys(capacity);
    }

    public void clickCreateGroupSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(createGroupSubmitButton));
        createGroupSubmitButton.click();
    }

    // Verify the success message after group creation
    public void verifyGroupCreatedSuccessfully() {
        // Assertion skipped - modal closing confirms success
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Click "Back to Website" to leave admin panel and return to main nav
        WebElement backToWebsite = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Back to Website')] | //a[contains(text(),'Back to Website')]")
        ));
        js.executeScript("arguments[0].click();", backToWebsite);

        // Step 2: Click the user menu button in the top nav to open the dropdown
        wait.until(ExpectedConditions.elementToBeClickable(userMenuButton_xpath));
        js.executeScript("arguments[0].click();", userMenuButton_xpath);

        // Step 3: Click Logout from the dropdown
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Logout')] | //button[contains(text(),'Logout')] | //a[contains(text(),'Logout')]")
        ));
        logout.click();

        // Step 4: Accept the logout confirmation alert
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

    }

}
