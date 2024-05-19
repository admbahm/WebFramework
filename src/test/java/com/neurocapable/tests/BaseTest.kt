package com.neurocapable.tests

import com.neurocapable.utils.WebDriverManager
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class BaseTest {

    protected lateinit var driver: WebDriver

    @BeforeMethod
    fun setUp() {
        driver = WebDriverManager.getDriver()
    }

    @AfterMethod
    fun tearDown() {
        WebDriverManager.quitDriver()
    }
}

