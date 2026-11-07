package com.cityservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By email = By.xpath("//input[@type='email']");
    private final By password = By.xpath("//input[@type='password']");
    private final By loginButton = (By.xpath("//*[@data-testid='cs-login-submit']"));
    private final By loginTitle = (By.xpath("//h1[@data-testid='cs-login-title']"));
    private final By errorMessage = By.xpath("//div[text()='Invalid email or password']");
    private final By invalidDataErrorMessage = By.xpath("//div[text()='Invalid input data']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginTitle() {
        return driver.findElement(loginTitle).getText();
    }

    public String loginButtonTitle() {
        return driver.findElement(loginButton).getText();
    }

    public DashboardPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new DashboardPage(LoginPage.this.driver);
    }

    public DashboardPage login(String userEmail, String pass) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();

        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getInvalidDataErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidDataErrorMessage)).getText();
    }

    public void enterCredentials(String userEmail, String pass) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(pass);
    }
    //Generated via ChatGPT, didnt find any resolution for that.
    public boolean isEmailInvalid() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].validity.valueMissing;", driver.findElement(email));
    }

}