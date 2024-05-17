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

}