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

        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.titleContains("Kotlin"))

        Assert.assertTrue(driver.title.contains("Kotlin"))
    }

    @Test
    fun checkPageLoadPerformance() {
        // Record the start time in milliseconds
        val startTime = System.currentTimeMillis()

        // Navigate to the Wikipedia main page
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")

        // Wait until the page title contains "Wikipedia" or until the timeout of 5 seconds
        WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleContains("Wikipedia"))

        // Record the end time in milliseconds
        val endTime = System.currentTimeMillis()

        // Calculate the page load time
        val loadTime = endTime - startTime

        // Print the page load time
        println("Page load time: $loadTime ms")

        // Assert that the page load time is below the threshold (example: 3000 milliseconds)
        Assert.assertTrue(loadTime < 3000).toString()
    }

    @Test
    fun countTopWordsOccurrences() {
        // Define a wait time of 5 seconds
        val waitTime = Duration.ofSeconds(5)

        // Navigate to the Wikipedia main page
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")

        // Wait until the page title contains "Wikipedia"
        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))

        // Retrieve the page source (HTML content)
        val pageSource = driver!!.pageSource

        // Split the page source into words using a regex pattern to match non-word characters, filter out any blank entries, and convert all words to lowercase
        val words = pageSource.split(Regex("\\W+")).filter { it.isNotBlank() }.map { it.lowercase() }

        // Group the words and count the occurrences of each, then sort by count in descending order and take the top 10 most common words
        val wordCount = words.groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.take(10)

        // Calculate the total number of words
        val totalWords = words.size

        // Print the top 10 reoccurring words along with their occurrence counts and percentages
        println("Top 10 reoccurring words with their percentages:")
        wordCount.forEach { (word, count) ->
            val percentage = count * 100.0 / totalWords
            println("$word: $count occurrences (${String.format("%.2f", percentage)}%)")
        }

        // Assert that there is at least one word in the top 10 list
        Assert.assertTrue(wordCount.isNotEmpty())
    }

    @Test
    fun testSearchKotlinMayReferTo() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        homePage.searchFor("Kotlin")

        // Wait for the page to load and check the presence of the text "Kotlin may refer to:"
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Kotlin may refer to:')]")))

        Assert.assertTrue(element.text.contains("Kotlin may refer to:"))
    }
}
