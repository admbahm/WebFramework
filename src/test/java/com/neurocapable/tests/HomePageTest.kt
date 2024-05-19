package com.neurocapable.tests

import com.neurocapable.pages.HomePage
import com.neurocapable.utils.WebDriverManager
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.Duration

class HomePageTest : BaseTest() {

    private lateinit var homePage: HomePage

    @BeforeMethod
    fun setUpTest() {
        driver = WebDriverManager.getDriver()
        homePage = HomePage(driver)
    }

    @AfterMethod
    fun tearDownTest() {
        WebDriverManager.quitDriver()
    }

    @Test
    fun testSearch() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        homePage.searchFor("Kotlin")

        // Proper use of WebDriverWait and ExpectedConditions
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.titleContains("Kotlin"))

        Assert.assertTrue(driver.title.contains("Kotlin"))
    }

    @Test
    fun testSearchKotlin() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        homePage.searchFor("Kotlin")

        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.titleContains("Kotlin"))

        Assert.assertTrue(driver.title.contains("Kotlin"))
    }
}
