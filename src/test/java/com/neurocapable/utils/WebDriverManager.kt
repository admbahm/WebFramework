package com.neurocapable.utils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

object WebDriverManager {
    private var driver: WebDriver? = null

    fun getDriver(): WebDriver {
        if (driver == null) {
            System.setProperty("webdriver.msedgedriver.driver", "src/main/java/com/neurocapable/drivers/msedgedriver")
            driver = EdgeDriver()
        }
        return driver!!
    }

    fun quitDriver() {
        driver?.quit()
        driver = null
    }
}
