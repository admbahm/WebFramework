package com.neurocapable.stepdefinitions;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepDefinitions implements En {
    private WebDriver driver;

    public MyStepDefinitions() {
        // Define steps here using Cucumber expressions
        Given("^I am on the login page$", () -> {
            System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
            driver = new ChromeDriver();
            driver.get("https://your-website.com/login");
        });

        When("^I enter \"(.*)\" and \"(.*)\"$", (String username, String password) -> {
            // Implement the step here
        });

        And("^I click the \"Login\" button$", () -> {
            // Implement the step here
        });

        Then("^I should see the message \"(.*)\"$", (String expectedMessage) -> {
            // Implement the step here
        });
    }
}