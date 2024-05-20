package com.neurocapable.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class HomePage(driver: WebDriver) : BasePage(driver) {

    @FindBy(id = "mp-welcome")
    lateinit var welcomeSection: WebElement

    private val searchInputLocator = By.id("searchInput")

    init {
        PageFactory.initElements(driver, this)
    }

    fun searchFor(term: String) {
        // Refetch searchInput element to avoid StaleElementReferenceException
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInputLocator))
        val searchInput = driver.findElement(searchInputLocator)

        searchInput.sendKeys(term)
        searchInput.submit()
    }

    fun getWelcomeText(): String {
        // Wait for the welcomeSection to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.visibilityOf(welcomeSection))
        return welcomeSection.text
    }

    fun getSearchInput(): WebElement {
        // Wait for the search input to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        return wait.until(ExpectedConditions.presenceOfElementLocated(searchInputLocator))
    }

    fun getNavLink(linkText: String): WebElement {
        // Wait for the navigation link to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)))
    }

    fun getFeaturedArticle(): WebElement {
        // Wait for the featured article section to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mp-tfa")))
    }

    fun getLanguageLink(language: String): WebElement {
        // Wait for the language link to be present
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(language)))
    }

    fun refetchElements() {
        PageFactory.initElements(driver, this)
    }
}
