package com.dharbor.core.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class contains common UI Methods that are used in Selenium WebDriver
 */
public class UIMethods {
    private final static int IMPLICIT_WAIT = 15;
    private final static int EXPLICIT_WAIT = 30;
    private static Logger log = Logger.getLogger("UIMethods");

    /**
     * Returns true if the element is present in the DOM.
     *
     * @param byElement {@link org.openqa.selenium.By}
     * @return boolean.
     */
    public static boolean isElementPresent(By byElement) {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        try {
            driver.findElement(byElement);
            return true;
        } catch (NoSuchElementException e) {
            log.info("Element not found.");
            return false;
        }
    }

    /**
     * Waits for an element to be removed.
     *
     * @param elementBy {@link By}
     * @return boolean.
     */
    public static void waitForElementRemoved(By elementBy) {
        WebDriverWait wait = DriverManager.getInstance().getWait();
        try {
            wait.withTimeout(Duration.ofMillis(EXPLICIT_WAIT));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
        } catch (TimeoutException e) {
            log.error("Element wasn't removed.");
        } finally {
            wait.withTimeout(Duration.ofSeconds(IMPLICIT_WAIT));
        }
    }

    /**
     * Returns the WebElement corresponding to the given locator.
     *
     * @param elementBy {@link By}
     * @return WebElement {@link WebElement}
     */
    public static WebElement getElement(By elementBy) {
        WebDriverWait wait = DriverManager.getInstance().getWait();
        try {
            wait.withTimeout(Duration.ofMillis(EXPLICIT_WAIT));
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        } catch (TimeoutException e) {
            log.error("Element was not found.");
            return null;
        } finally {
            wait.withTimeout(Duration.ofSeconds(IMPLICIT_WAIT));
        }
    }

    /**
     * Moves the mouse to the given WebElement.
     *
     * @param element {@link WebElement}
     */
    public static void moveMouseToElement(WebElement element) {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    /**
     * Scrolls the browser to the given WebElement.
     *
     * @param element {@link WebElement}
     */
    public static void scrollToElement(WebElement element) {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}