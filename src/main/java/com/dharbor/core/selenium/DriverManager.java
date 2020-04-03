package com.dharbor.core.selenium;

import com.dharbor.core.Environment;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * This class contains Selenium WebDriver and WebDriverWait configuration.
 */
public class DriverManager {
    private Logger log = Logger.getLogger(getClass());
    private WebDriver driver;
    private WebDriverWait wait;
    private String browser = Environment.getInstance().getBrowser();
    private static DriverManager instance = null;

    public DriverManager() {
        initialize();
    }

    /**
     * Gets an instance of DriverManager class.
     * @return DriverManager
     */
    public static DriverManager getInstance() {
        if((instance == null) || (instance.driver == null)) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Initializes the web driver and opens the browser.
     */
    private void initialize() {
        log.info("Initialize Web Driver");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriverManager.getInstance().version("79.0.3945.36").setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance().version("0.23.0").setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            InternetExplorerDriverManager.getInstance().version("3.14.0").setup();
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Returns the current WebDriverWait instance.
     * @return WebDriverWait
     */
    public WebDriverWait getWait() {
        return wait;
    }

    /**
     * Returns the current WebDriver instance.
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Quits from the current web driver.
     */
    public void quitDriver() {
        try {
            driver.quit();
        } catch (Exception e) {
            log.error("Unable to quit web driver");
        }
    }
}