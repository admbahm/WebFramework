package com.neurocapable;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.snippets.SnippetType;
import io.cucumber.java8.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "classpath:stepdefinitions",
        snippets = SnippetType.CAMELCASE,
        dryRun = false, // Set to true for initial development; set to false before running tests
        monochrome = true // Makes output easier to read
)
public class TestRunner {
    // This class remains empty; Cucumber runs the tests
}

