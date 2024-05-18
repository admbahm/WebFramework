package com.neurocapable

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.Test
import org.testng.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.Duration

class WelcomeWikiPage : Base() {

    @Test
    fun welcomePageAssets() {
        val waitTime = Duration.ofSeconds(5)
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")
        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))
    }

//    @Test
//    fun countTheOccurrences() {
//        val waitTime = Duration.ofSeconds(5)
//        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")
//        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))
//
//        val pageSource = driver!!.pageSource
//        val word = "the"
//        val regex = Regex("\\b$word\\b", RegexOption.IGNORE_CASE)
//        val count = regex.findAll(pageSource).count()
//
//        println("The word '$word' occurs $count times.")
//        Assert.assertTrue(count > 0) // This assertion is just to ensure that there is at least one occurrence
//    }

    @Test
    fun countTheOccurrences() {
        // Define a wait time of 5 seconds
        val waitTime = Duration.ofSeconds(5)

        // Navigate to the Wikipedia main page
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")

        // Wait until the page title contains "Wikipedia"
        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))

        // Retrieve the page source (HTML content)
        val pageSource = driver!!.pageSource

        // Define the word to search for and create a regex pattern for it
        val word = "the"
        val regex = Regex("\\b$word\\b", RegexOption.IGNORE_CASE)

        // Find all matches of the word in the page source and convert them to a list
        val matches = regex.findAll(pageSource).toList()

        // Count the number of matches
        val count = matches.size

        // Print the total number of occurrences of the word
        println("The word '$word' occurs $count times.")

        // Iterate over each match to print the context around the word
        matches.forEach { match ->
            val start = match.range.first // Start index of the match
            val end = match.range.last // End index of the match

            // Extract a substring of 20 characters before and after the match, within bounds of the page source length
            val context =
                pageSource.substring((start - 20).coerceAtLeast(0), (end + 20).coerceAtMost(pageSource.length - 1))

            // Print the context around the word
            println("Context: $context")
        }

        // Assert that there is at least one occurrence of the word
        Assert.assertTrue(count > 0)
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
}


