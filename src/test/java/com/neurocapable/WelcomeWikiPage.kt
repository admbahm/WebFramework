package com.neurocapable

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.Test
import org.testng.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.Duration

class WelcomeWikiPage : Base(){

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
        val waitTime = Duration.ofSeconds(5)
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")
        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))

        val pageSource = driver!!.pageSource
        val word = "the"
        val regex = Regex("\\b$word\\b", RegexOption.IGNORE_CASE)
        val matches = regex.findAll(pageSource).toList()
        val count = matches.size

        println("The word '$word' occurs $count times.")
        matches.forEach { match ->
            val start = match.range.first
            val end = match.range.last
            val context = pageSource.substring((start - 20).coerceAtLeast(0), (end + 20).coerceAtMost(pageSource.length - 1))
            println("Context: $context")
        }

        Assert.assertTrue(count > 0) // This assertion is just to ensure that there is at least one occurrence
    }

    @Test
    fun countTopWordsOccurrences() {
        val waitTime = Duration.ofSeconds(5)
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")
        WebDriverWait(driver, waitTime).until(ExpectedConditions.titleContains("Wikipedia"))

        val pageSource = driver!!.pageSource
        val words = pageSource.split(Regex("\\W+")).filter { it.isNotBlank() }.map { it.lowercase() }
        val wordCount = words.groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.take(10)

        val totalWords = words.size
        println("Top 10 reoccurring words with their percentages:")
        wordCount.forEach { (word, count) ->
            val percentage = count * 100.0 / totalWords
            println("$word: $count occurrences (${String.format("%.2f", percentage)}%)")
        }

        Assert.assertTrue(wordCount.isNotEmpty()) // Ensure there are words counted
    }


}