package com.automation.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.testng.Assert;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Existing Locators
    private By inventoryContainer = By.id("inventory_container");
    private By addFirstItemBtn = By.className("btn_inventory");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    
    // New Locators for Scenarios 4 and 5
    private By sortDropdown = By.className("product_sort_container");
    private By inventoryPrices = By.className("inventory_item_price");
    private By removeFirstItemBtn = By.xpath("//button[text()='Remove']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean checkInventoryGridVisibility() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }

    public String retrieveCurrentPageTitle() {
        return driver.getTitle();
    }

    public void addFirstItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addFirstItemBtn)).click();

    }

    public String retrieveCartItemsBadgeCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge)).getText();
    }

    // New Method: Handles selecting from a standard HTML select dropdown
    public void selectSortOptionText(String optionText) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
    }

    // New Method: Captures first price to verify low-to-high sorting
    public double getFirstProductPrice() {
        List<WebElement> prices = driver.findElements(inventoryPrices);
        String priceText = prices.get(0).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    // New Method: Removes item from cart
    public void removeFirstItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFirstItemBtn)).click();
    }

    // New Method: Verifies badge disappears completely
    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(shoppingCartBadge).isEmpty();
    }
}