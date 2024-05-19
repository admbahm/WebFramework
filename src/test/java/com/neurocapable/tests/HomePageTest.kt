package com.neurocapable.tests

import com.neurocapable.pages.HomePage
import org.testng.Assert
import org.testng.annotations.Test

class HomePageTest : BaseTest() {

    @Test
    fun testSearch() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page")
        val homePage = HomePage(driver)
        homePage.searchFor("Kotlin")

        Assert.assertTrue(driver.title.contains("Kotlin"))
    }
}
