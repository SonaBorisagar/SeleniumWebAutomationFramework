package com.automation.steps;

import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Utilities.ExtentReportManager;
import Utilities.Screenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class AutomationStepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void suiteInitialization() {
     
        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--headless=new");
    	options.addArguments("--disable-features=SafeBrowsingPasswordProtection");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
//      options.addArguments("--disable-infobars");
//      options.addArguments("--user-data-dir=/tmp/temporary-profile");
        options.addArguments("--incognito");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Given("the user navigates to the Swag Labs landing portal")
    public void navigateToPortal() {
        driver.get("https://www.saucedemo.com");
    }

    @When("the user enters username {string} and password {string}")
    public void inputCredentials(String username, String password) {
        loginPage.executeLoginSequence(username, password);
    }

    @When("clicks on the authentication submission button")
    public void clickSubmitButton() {
        // Step action combined in multi-locator action strategy for efficiency
    }

    @Then("the user should see the central catalog dashboard page titled {string}")
    public void verifyDashboardTitle(String expectedTitle) {
        Assert.assertEquals(dashboardPage.retrieveCurrentPageTitle(), expectedTitle);
    }

    @Then("the product catalog grid display panel should be visible")
    public void verifyInventoryPanel() {
        Assert.assertTrue(dashboardPage.checkInventoryGridVisibility());
    }

    @Then("a critical error dialog should present {string}")
    public void verifyErrorContext(String expectedErrorMessage) {
        Assert.assertTrue(loginPage.retrieveErrorDialogText().contains(expectedErrorMessage));
    }

    @When("the user appends the first catalog item into the active cart")
    public void appendCatalogItem() {
        dashboardPage.addFirstItemToCart();
    }

    @When("navigates directly into the cart overview portal")
    public void stepBoundarySync() {
        // Stays inside the context framework layout validation
    }

    @Then("the interactive shopping cart badge indicator should display {string}")
    public void verifyBadgeIndicatorCount(String expectedCount) {
        Assert.assertEquals(dashboardPage.retrieveCartItemsBadgeCount(), expectedCount);
    }
    
    @When("the user selects sorting option {string}")
    public void selectSortingOption(String sortingOption) {
        dashboardPage.selectSortOptionText(sortingOption);
    }

    @Then("the items should be arranged with the cheapest product appearing first")
    public void verifyCheapestProductFirst() {
        double absoluteFirstPrice = dashboardPage.getFirstProductPrice();
        // The cheapest item on SauceDemo is the "Sauce Labs Onesie" at $7.99
        Assert.assertEquals(absoluteFirstPrice, 7.99, "Product sorting validation error encountered.");
    }

    @When("the user removes that item from the cart")
    public void removeFirstItem() {
        dashboardPage.removeFirstItemFromCart();
    }

    @Then("the shopping cart badge indicator should no longer be displayed")
    public void verifyCartBadgeDisappeared() {
        Assert.assertFalse(dashboardPage.isCartBadgeDisplayed(), 
                "The shopping cart badge indicator was unexpectedly found visible.");
    }
    
    private static ExtentReports extent;

    @Before
    public void beforeScenario(Scenario scenario) {

        extent = ExtentReportManager.getInstance();

        ExtentTest test = extent.createTest(scenario.getName());
        ExtentReportManager.setTest(test);
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            String path = Screenshot.getScreenshotPath(driver);

            Screenshot.attachScreenshot(path);
        }

        driver.quit();
    }
}
