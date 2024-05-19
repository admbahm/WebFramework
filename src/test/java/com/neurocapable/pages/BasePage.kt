package com.neurocapable.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

open class BasePage(protected val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    fun getTitle(): String {
        return driver.title
    }
}
