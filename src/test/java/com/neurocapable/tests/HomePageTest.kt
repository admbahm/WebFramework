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
import org.openqa.selenium.By

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

        // Proper use of WebDriverWait and ExpectedConditions
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.titleContains("Kotlin"))

        Assert.assertTrue(driver.title.contains("Kotlin"))
    }

    @Test
    fun testSearchKotlinMayReferTo() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        homePage.searchFor("Kotlin")

        // Increase the wait time and check for the correct condition
        val wait = WebDriverWait(driver, Duration.ofSeconds(20))
        val element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//p[contains(text(), 'Kotlin may refer to:')]")))

        Assert.assertTrue(element.text.contains("Kotlin may refer to:"))
    }

    @Test
    fun testWelcomeSection() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")

        // Verify the welcome section text
        val welcomeText = homePage.getWelcomeText()
        Assert.assertTrue(welcomeText.contains("Welcome to Wikipedia"), "The welcome text does not contain the expected content.")
    }
}
