package com.neurocapable.tests

import com.neurocapable.pages.HomePage
import com.neurocapable.utils.WebDriverManager
import org.openqa.selenium.By
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

    @Test
    fun testSearchSuggestionDropdown() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        val searchInput = homePage.getSearchInput()
        searchInput.sendKeys("Kotlin")

        // Wait for the suggestion dropdown to appear
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val suggestions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".suggestions-result")))

        Assert.assertTrue(suggestions.text.contains("Kotlin"), "The search suggestions do not contain the expected text.")
    }

    @Test
    fun testNavigationLinks() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")

        // Check for the presence of navigation links
        val contentsLink = homePage.getNavLink("Contents")
        Assert.assertNotNull(contentsLink, "Contents link is not present")

        val currentEventsLink = homePage.getNavLink("Current events")
        Assert.assertNotNull(currentEventsLink, "Current events link is not present")

        val randomArticleLink = homePage.getNavLink("Random article")
        Assert.assertNotNull(randomArticleLink, "Random article link is not present")

        // Verify the links lead to the correct pages
        contentsLink.click()
        Assert.assertTrue(driver.title.contains("Portal:Contents"), "Navigation to Contents page failed")

        driver.navigate().back()

        currentEventsLink.click()
        Assert.assertTrue(driver.title.contains("Portal:Current events"), "Navigation to Current events page failed")

        driver.navigate().back()

        randomArticleLink.click()
        Assert.assertTrue(driver.title.contains("Special:Random"), "Navigation to Random article page failed")
    }

    @Test
    fun testFeaturedArticleSection() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")

        // Check for the presence of the featured article section
        val featuredArticle = homePage.getFeaturedArticle()
        Assert.assertNotNull(featuredArticle, "Featured article section is not present")

        // Verify the featured article contains expected content
        Assert.assertTrue(featuredArticle.text.contains("From today's featured article"), "Featured article section does not contain expected content")
    }

    @Test
    fun testLanguageLinks() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")

        // Check for the presence of language links
        val languageLink = homePage.getLanguageLink("Español")
        Assert.assertNotNull(languageLink, "Spanish language link is not present")

        // Verify the language link leads to the correct page
        languageLink.click()
        Assert.assertTrue(driver.title.contains("Wikipedia, la enciclopedia libre"), "Navigation to Spanish Wikipedia failed")
    }
}
