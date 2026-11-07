package com.cityservices.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.Map;


public class BasePage {
    protected WebDriver driver;

    private final String baseUrl = "http://localhost:5173";

    public void getUrl() {
        driver.get(baseUrl);
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of("profile.password_manager_leak_detection", false));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    void tearDown() {
            driver.quit();
        }
    }
