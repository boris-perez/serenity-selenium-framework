package com.dharbor.ui;

import com.dharbor.core.Environment;
import com.dharbor.core.selenium.DriverManager;
import com.dharbor.ui.pages.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

/**
 * This class contains all the methods to navigate through pages.
 */
public class PageTransporter {
    private Logger log = Logger.getLogger(getClass());
    private static PageTransporter instance;

    /**
     * Initializes PageTransporter.
     */
    public PageTransporter() {
        log.info("Initialize Page Transporter");
    }

    /**
     * Gets an instance of PageTransporter.
     * @return PageTransporter
     */
    public static PageTransporter getInstance() {
        if (instance == null) {
            instance = new PageTransporter();
        }
        return instance;
    }

    /**
     * Goes to the given URL.
     * @param url
     */
    public void goToURL(String url) {
        log.info(String.format("URL: %s", url));
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        try {
            driver.navigate().to(url);
        } catch (TimeoutException e) {
            log.error(String.format("Unable to navigate to URL: %s", url));
        }
    }

    /**
     * Goes to the home page.
     * @return HomePage
     */
    public HomePage goToHomePage() {
        goToURL(Environment.getInstance().getHomeURL());
        return new HomePage();
    }
}
