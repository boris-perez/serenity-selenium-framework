package com.dharbor.ui.pages;

import com.dharbor.core.selenium.UIMethods;
import com.dharbor.ui.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains the methods to interact with the ShoppingCartPage.
 */
public class ShoppingCartPage extends BasePageObject {
    private final String PRODUCT_LABEL_XPATH =
            "//table[@id='cart_summary']//a[contains(text(), '%s')]";
    private final String PRODUCT_AVAILABILITY_LABEL_XPATH = PRODUCT_LABEL_XPATH
            + "//ancestor::tr/td/span[text()='%s']";
    private final String REMOVE_PRODUCT_BUTTON_XPATH = PRODUCT_LABEL_XPATH
            + "//ancestor::tr/td//a[@title='Delete']";
    private By totalPriceLabelBy = By.id("total_price");

    @FindBy(id = "cart_title")
    WebElement cartPageHeader;

    /**
     * Constructor for ShoppingCartPage.
     */
    public ShoppingCartPage() {
        waitForPageLoaded();
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(cartPageHeader));
    }

    /**
     * Returns true if the given product is displayed, false otherwise.
     *
     * @param productName
     * @return boolean
     */
    public boolean isProductDisplayed(String productName) {
        By productLabelBy = By.xpath(String.format(PRODUCT_LABEL_XPATH, productName));
        return UIMethods.isElementPresent(productLabelBy);
    }

    /**
     * Returns true if the given product availability is displayed, false otherwise.
     *
     * @param productName
     * @param availability
     * @return boolean
     */
    public boolean isProductAvailabilityDisplayed(String productName, String availability) {
        By productAvailabilityLabelBy = By.xpath(String.format(PRODUCT_AVAILABILITY_LABEL_XPATH,
                productName, availability));
        return UIMethods.isElementPresent(productAvailabilityLabelBy);
    }

    /**
     * Removes the given product from cart.
     *
     * @param productName
     */
    public void removeProductFromCart(String productName) {
        By removeProductButtonBy = By.xpath(String.format(REMOVE_PRODUCT_BUTTON_XPATH, productName));
        By productLabelBy = By.xpath(String.format(PRODUCT_LABEL_XPATH, productName));
        WebElement removeProductButton = UIMethods.getElement(removeProductButtonBy);
        removeProductButton.click();
        UIMethods.waitForElementRemoved(removeProductButtonBy);
        UIMethods.waitForElementRemoved(productLabelBy);
    }

    /**
     * Return the total price.
     *
     * @return String
     */
    public String getTotalPrice() {
        WebElement totalPriceLabel = UIMethods.getElement(totalPriceLabelBy);
        return totalPriceLabel.getText();
    }
}
