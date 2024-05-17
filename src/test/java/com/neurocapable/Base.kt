package com.neurocapable

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

open class Base {

    var driver:WebDriver? = null

    @BeforeTest
    fun setUp() {
        System.setProperty("webdriver.msedgedriver.driver", "src/main/java/com/neurocapable/drivers/msedgedriver")
        driver = EdgeDriver()
    }

    @AfterTest
    fun tearDown() {
        driver!!.quit()
    }
}