package com.neurocapable.tests

import com.neurocapable.utils.WebDriverManager
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

open class BaseTest {

    protected lateinit var driver: WebDriver

    @BeforeTest
    fun setUp() {
        driver = WebDriverManager.getDriver()
    }

    @AfterTest
    fun tearDown() {
        WebDriverManager.quitDriver()
    }
}
