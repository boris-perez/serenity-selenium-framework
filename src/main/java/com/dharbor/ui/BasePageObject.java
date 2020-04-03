package com.dharbor.ui;

import com.dharbor.core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains the common fields and methods to interact with the application.
 */
public abstract class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;

    public BasePageObject() {
        driver = DriverManager.getInstance().getWebDriver();
        wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
        log = Logger.getLogger(getClass());
    }

    /**
     * Waits for the page to be loaded.
     */
    public abstract void waitForPageLoaded();
}
