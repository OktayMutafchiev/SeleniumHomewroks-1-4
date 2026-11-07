package com.cityservices.tests;

import com.cityservices.pages.BasePage;
import com.cityservices.pages.DashboardPage;
import com.cityservices.pages.LoginPage;
import com.cityservices.pages.NewRequestPage;
import org.junit.jupiter.api.Test;

public class OpenNewRequest extends BasePage {
    @Test
    public void openNewRequest_HighPriority() {
        // Arrange
        getUrl();

        LoginPage loginPageTest = new LoginPage(driver);
        loginPageTest.login("john@example.com", "password123");

        DashboardPage dashboardPage = new DashboardPage(driver);

        // Act
        NewRequestPage newRequestPage = dashboardPage.openNewRequest();
        newRequestPage.addTitle("Test Request Title Test");
        newRequestPage.addDescription("Test Request Description - needs to be at least 30 chars.");
        newRequestPage.selectCategory("roads-and-infrastructure");
        newRequestPage.selectPriority("HIGH");
        newRequestPage.clickBasicInfoNextButton();
        newRequestPage.addStreetAddress("123 Main St, Anytown, USA");
        newRequestPage.addCity("Cardiff");
        newRequestPage.addPostalCode("CF10 1AA");
        newRequestPage.addLocationTextField("Near the intersection of Main St and 1st Ave");
        newRequestPage.clickLocationPageNextButton();
        newRequestPage.selectContactMethod("PHONE");
        newRequestPage.addEmail("test123@mail.com");
        newRequestPage.addPhoneNumber(123456789);
        newRequestPage.clickContactInfoNextButton();
        newRequestPage.clickCheckBoxButton();
        newRequestPage.clickAdditionalInfoPageNextButton();
        newRequestPage.clickSubmitButton();

        String titleOfTheRequest = "Test Request Title Test";





    }

}
