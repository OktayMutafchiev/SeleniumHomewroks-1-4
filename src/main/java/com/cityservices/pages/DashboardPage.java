package com.cityservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final By userMenuButton = By.xpath("//button[@data-testid='cs-user-menu-button']");
    private final By userMenuRole = By.xpath("//div[@data-testid='cs-user-menu']//span[contains(@class,'MuiChip-label')]");
    private final By newRequestButton = By.xpath("//a[@data-testid='cs-nav-new-request']");
    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getUserRole() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(userMenuButton)).click();

        WebElement role = wait.until(ExpectedConditions.visibilityOfElementLocated(userMenuRole));

        return role.getText();
    }

    public NewRequestPage openNewRequest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(newRequestButton)
        );

        button.click();

        return new NewRequestPage(driver);
    }

}
