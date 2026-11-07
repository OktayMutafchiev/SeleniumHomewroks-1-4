package com.cityservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

public class NewRequestPage {
    private final WebDriver driver;

    private final By requestTitleField = By.xpath("//input[@name='title']");
    private final By descriptionField = By.xpath("//textarea[@name='description']");
    private final By categoryDropdown = By.xpath("//div[@id='mui-component-select-category']");
    private final By basicInfoPage_nextButton = By.xpath("//button[@data-testid='cs-new-request-wizard-next']");
    private final By streetAddressField = By.xpath("//input[@name='streetAddress']");
    private final By cityField = By.xpath("//input[@name='city']");
    private final By postalCodeField = By.xpath("//input[@name='postalCode']");
    private final By locationTextField = By.xpath("//input[@name='locationText']");
    private final By locationPage_nextButton = By.xpath("//button[@data-testid='cs-new-request-wizard-next']");
    private final By contactPage_contactMethodDropdown = By.xpath("//div[@id='mui-component-select-contactMethod']");
    private final By contactPage_emailField = By.xpath("//input[@name='email']");
    private final By contactPage_phoneNumberField = By.xpath("//input[@name='phone']");
    private final By contactPage_nextButton = By.xpath("//button[@data-testid='cs-new-request-wizard-next']");
    private final By additionalInfoPage_checkBox = By.xpath("//span[@data-testid='cs-new-request-agree-terms']");
    private final By additionalInfoPage_nextButton = By.xpath("//button[@data-testid='cs-new-request-wizard-next']");
    private final By submitButton = By.xpath("//button[@data-testid='cs-new-request-wizard-submit']");




    public NewRequestPage(WebDriver driver) {
        this.driver = driver;
    }
    public void addTitle(String title) {

        WebElement titleField = driver.findElement(requestTitleField);
        titleField.click();
        titleField.clear();
        titleField.sendKeys(Keys.CONTROL, "a");
        titleField.sendKeys(title);
    }

    public void addDescription(String description) {
        WebElement decriptionField = driver.findElement(descriptionField);
        decriptionField.sendKeys(description);
    }

    public void selectCategory(String category) {

        driver.findElement(categoryDropdown).click();

        By categoryOption = By.xpath(
                "//li[@data-value='" + category + "']"
        );

        driver.findElement(categoryOption).click();
    }

    public void selectPriority(String priority) {
        By priorityOption = By.xpath(
                "//input[@name='priority' and @value='" + priority + "']"
        );

        driver.findElement(priorityOption).click();
    }

    public void clickBasicInfoNextButton() {
        driver.findElement(basicInfoPage_nextButton).click();
    }

    public void addStreetAddress(String streetAddress) {
        WebElement addStreetAddress = driver.findElement(streetAddressField);
        addStreetAddress.sendKeys(streetAddress);
    }
    public void addCity(String cityName) {
        WebElement addStreetAddress = driver.findElement(cityField);
        addStreetAddress.sendKeys(cityName);
    }
    public void addPostalCode(String postalCode) {
        WebElement addPostalCode = driver.findElement(postalCodeField);
        addPostalCode.sendKeys(postalCode);
    }
    public void addLocationTextField(String locationText){
        WebElement addLocationTextField = driver.findElement(locationTextField);
        addLocationTextField.sendKeys(locationText);
    }
    public void clickLocationPageNextButton() {
        driver.findElement(locationPage_nextButton).click();
    }

    public void selectContactMethod(String method) {
        By contactDropdown = By.id("mui-component-select-contactMethod");

        driver.findElement(contactDropdown).click();

        By methodOption = By.xpath(
                "//li[@data-value='" + method + "']"
        );

        driver.findElement(methodOption).click();
    }
    public void addPhoneNumber(int phoneNumber){
        WebElement addLocationTextField = driver.findElement(contactPage_phoneNumberField);
        addLocationTextField.sendKeys(String.valueOf(phoneNumber));
    }
    public void addEmail(String email){
        WebElement addLocationTextField = driver.findElement(contactPage_emailField);
        addLocationTextField.sendKeys(email);
    }
    public void clickContactInfoNextButton() {
        driver.findElement(contactPage_nextButton).click();
    }
    public void clickCheckBoxButton() {
        driver.findElement(additionalInfoPage_checkBox).click();
    }
    public void clickAdditionalInfoPageNextButton() {
        driver.findElement(additionalInfoPage_nextButton).click();
    }
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

}
