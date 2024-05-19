package com.neurocapable.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : BasePage(driver) {

    @FindBy(id = "searchInput")
    lateinit var searchInput: WebElement

    fun searchFor(term: String) {
        searchInput.sendKeys(term)
        searchInput.submit()
    }
}
