package com.cityservices.tests;

import com.cityservices.pages.BasePage;
import com.cityservices.pages.DashboardPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginPageTest extends BasePage {
    @Test
    public void verifyLoginPageTitle() {
        getUrl();

        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        String pageTitle = loginPage.getLoginTitle();

        Assertions.assertEquals("City Services Login", pageTitle, "Wrong page title: " + pageTitle);

        String loginTitle = loginPage.loginButtonTitle();
        Assertions.assertEquals("Login", loginTitle, "Login button text should be Login");
        loginPage.login("admin@city.gov", "password123");
        loginPage.clickLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getUserRole();
    }

    @ParameterizedTest
    @CsvSource({"ADMIN, admin@city.gov, City Services Portal",
            "CITIZEN, john@example.com, City Services Portal",
            "CLERK, mary.clerk@city.gov, City Services Portal",
            "SUPERVISOR,supervisor@city.gov, City Services Portal",
            "FIELD_AGENT, field.agent@city.gov, City Services Portal"})
    public void verifyLogin(String expectedRole, String email, String expectedPage) {

        // Arrange
        getUrl();

        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        // Act
        loginPage.login(email, "password123");
        DashboardPage dashboardPage = new DashboardPage(driver);
        String actualRole = dashboardPage.getUserRole();
        String actualPage = driver.getTitle();

        // Assert
        Assertions.assertEquals(expectedRole, actualRole, "Incorrect role for: " + email);
        Assertions.assertEquals(expectedPage, actualPage, "Incorrect page after login for: " + email);
    }

    @ParameterizedTest
    @CsvSource({"admin@city.gov, wrongPassword, Invalid email or password"})
    public void invalidLogin_wrongPassword(String email, String password, String expectedError) {

        // Arrange
        getUrl();
        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        // Act
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        // Assert
        String actualError = loginPage.getErrorMessage();
        Assertions.assertEquals(expectedError, actualError, "Incorrect error message for password: " + password);
    }

    @ParameterizedTest
    @CsvSource({"unknown@city.gov, password123, Invalid email or password"})
    public void invalidLogin_wrongEmail(String email, String password, String expectedError) {

        // Arrange
        getUrl();
        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        // Act
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        // Assert
        String actualError = loginPage.getErrorMessage();
        Assertions.assertEquals(expectedError, actualError, "Incorrect error message for email: " + email);
    }

    @ParameterizedTest
    @CsvSource({"unknown@city.gov312, password123, Invalid input data"})
    public void invalidLogin_invalidInputData(String email, String password, String expectedError) {

        // Arrange
        getUrl();
        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        // Act
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        // Assert
        String actualError = loginPage.getInvalidDataErrorMessage();
        Assertions.assertEquals(expectedError, actualError, "Incorrect error message for email: " + email);
    }

    @Test
    public void emptyEmail_displaysValidationError() {

        // Arrange
        getUrl();
        com.cityservices.pages.LoginPage loginPage = new com.cityservices.pages.LoginPage(driver);

        // Act
        loginPage.enterCredentials("", "password123");
        loginPage.clickLoginButton();

        // Assert
        Assertions.assertTrue(loginPage.isEmailInvalid(), "Empty email should trigger browser validation");
    }
}
