package com.neurocapable

import org.testng.annotations.Test
import org.testng.Assert

class WelcomeWikiPage : Base(){

    @Test
    fun welcomePageAssets() {
        driver!!.get("https://en.wikipedia.org/wiki/Main_Page")
        Assert.assertEquals(driver!!.currentUrl, "https://en.wikipedia.org/wiki/Main_Page")
        Thread.sleep(5000)
    }
}