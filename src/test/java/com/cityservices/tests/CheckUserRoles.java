package com.cityservices.tests;

import com.cityservices.pages.BasePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckUserRoles extends BasePage {

    @Test
    public void verifyCitizenDemoRole() {
        verifyRole("Citizen", "CITIZEN");
    }

    @Test
    public void verifyClerkDemoRole() {
        verifyRole("Clerk", "CLERK");
    }

    @Test
    public void verifySupervisorDemoRole() {
        verifyRole("Supervisor", "SUPERVISOR");
    }

    @Test
    public void verifyFieldAgentDemoRole() {
        verifyRole("Field Agent", "FIELD_AGENT");
    }

    @Test
    public void verifyAdministratorDemoRole() {
        verifyRole("Administrator", "ADMIN");
    }

    public void verifyRole(String demoRole, String expectedRole) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        driver.get("http://localhost:5173/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement demoLogin = driver.findElement(By.xpath(
                "//button[@aria-label='Prefill login form with "
                        + demoRole + " demo account credentials']"
        ));

        demoLogin.click();

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));

        loginButton.click();

        WebElement userMenuButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-testid='cs-user-menu-button']")
                )
        );

        userMenuButton.click();

        // Get current user role
        WebElement userMenuRole = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='cs-user-menu']//span[contains(@class,'MuiChip-label')]")
                )
        );

        // Verify user role
        assertEquals(
                expectedRole,
                userMenuRole.getText(),
                "Incorrect user role!"
        );

        driver.quit();
    }
}