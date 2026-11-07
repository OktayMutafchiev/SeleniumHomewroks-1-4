package com.cityservices.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class RolesLoginTest {
    @Test
    public void testAllRoles() {

        String[] emails = {"john@example.com", "mary.clerk@city.gov", "supervisor@city.gov", "field.agent@city.gov", "admin@city.gov"};

        String[] roles = {"CITIZEN", "CLERK", "SUPERVISOR", "FIELD_AGENT", "ADMIN"};

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.setExperimentalOption("prefs", Map.of("profile.password_manager_leak_detection", false));

        WebDriver chromeDriver = new ChromeDriver(options);
        for (int i = 0; i < emails.length; i++) {

            chromeDriver.get("http://localhost:5173");

            chromeDriver.findElement(By.xpath("//input[@type='email']")).sendKeys(emails[i]);

            chromeDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("password123");

            chromeDriver.findElement(By.xpath("//*[@data-testid='cs-login-submit']")).click();

            WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='cs-user-menu-button']"))).click();
            By roleLocator = By.xpath("//ul[@role='menu']//li[@aria-disabled='true']//span[contains(@class,'MuiChip-label')]");

            String actualRole = wait.until(ExpectedConditions.visibilityOfElementLocated(roleLocator)).getText().trim();

            Assertions.assertEquals(roles[i].toUpperCase(), actualRole);

            chromeDriver.findElement(By.xpath("//*[@data-testid='cs-user-menu-logout']")).click();
        }

        chromeDriver.quit();
    }

}
