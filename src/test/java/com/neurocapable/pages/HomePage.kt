package com.neurocapable.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.openqa.selenium.By

class HomePage(driver: WebDriver) : BasePage(driver) {

    @FindBy(id = "searchInput")
    lateinit var searchInput: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun searchFor(term: String) {
        // Wait for the searchInput element to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")))

        searchInput.sendKeys(term)
        searchInput.submit()
    }
}