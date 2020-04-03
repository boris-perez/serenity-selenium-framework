package com.dharbor.ui.sections;

import com.dharbor.core.selenium.UIMethods;
import com.dharbor.ui.BasePageObject;
import com.dharbor.ui.layers.CartProductLayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains the methods to interact with the BestSellersSection.
 */
public class BestSellersSection extends BasePageObject {
    private final String PRODUCT_PRICE_REDUCTION_TAG_XPATH = "//ul[@id='blockbestsellers']//a[@title='%s']" +
            "/ancestor::li//div[@itemprop='offers'][parent::div[@class!='product-image-container']]" +
            "//span[@class='price-percent-reduction']";
    private final String ADD_PRODUCT_TO_CART_XPATH =
            "//ul[@id='blockbestsellers']//a[@title='%s']/ancestor::li//a[contains(@class, 'ajax_add_to_cart_button')]";

    private final String PRODUCT_IMAGE_XPATH =
            "//ul[@id='blockbestsellers']//a[@title='%s']/ancestor::li//img";

    @FindBy(xpath = "//li[@class='active'][./a[@href='#blockbestsellers']]")
    WebElement bestSellersTab;

    /**
     * Constructor for BestSellersSection.
     */
    public BestSellersSection() {
        waitForPageLoaded();
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(bestSellersTab));
    }

    /**
     * Returns the price reduction percentage for the given product.
     *
     * @param productName
     * @return String
     */
    public String getProductPriceReductionPercentage(String productName) {
        By productPriceReductionTagBy = By.xpath(String.format(PRODUCT_PRICE_REDUCTION_TAG_XPATH, productName));
        WebElement productImage = getProductImageWebElement(productName);
        UIMethods.scrollToElement(productImage);
        WebElement productPriceReductionTag = UIMethods.getElement(productPriceReductionTagBy);
        return productPriceReductionTag.getText();
    }

    /**
     * Adds the given product to cart
     *
     * @param productName
     * @return CartProductLayer
     */
    public CartProductLayer addProductToCart(String productName) {
        By addProductToCartBy = By.xpath(String.format(ADD_PRODUCT_TO_CART_XPATH, productName));
        WebElement productImage = getProductImageWebElement(productName);
        UIMethods.moveMouseToElement(productImage);
        WebElement addProductToCart = UIMethods.getElement(addProductToCartBy);
        addProductToCart.click();
        return new CartProductLayer();
    }

    /**
     * Returns the image for the given product.
     *
     * @param productName
     * @return WebElement
     */
    private WebElement getProductImageWebElement(String productName) {
        return UIMethods.getElement(By.xpath(String.format(PRODUCT_IMAGE_XPATH, productName)));
    }
}
