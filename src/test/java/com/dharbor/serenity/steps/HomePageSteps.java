package com.dharbor.serenity.steps;

import com.dharbor.ui.PageTransporter;
import com.dharbor.ui.layers.CartProductLayer;
import com.dharbor.ui.pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.assertEquals;

public class HomePageSteps {
    private PageTransporter pageTransporter = PageTransporter.getInstance();

    //Pages
    private HomePage homePage;
    private CartProductLayer cartProductLayer;

    @Given("^I navigate to the Home page$")
    public void navigateToHomePage() {
        homePage = pageTransporter.goToHomePage();
    }

    @When("^I go to Best Sellers section$")
    public void goToBestSellersSection() {
        homePage.clickBestSellersTab();
    }

    @Then("^the product \"(.*?)\" should have a discount of \"(.*?)\"$")
    public void verifyProductHasDiscount(String productName, String expectedProductDiscount) {
        String actualProductDiscount = homePage.bestSellersSection.getProductPriceReductionPercentage(productName);
        assertEquals(actualProductDiscount, expectedProductDiscount, "Product discount percentage didn't match.");
    }

    @When("^I add the product \"(.*?)\" to cart$")
    public void addProductToCart(String productName) {
        cartProductLayer = homePage.bestSellersSection.addProductToCart(productName);
    }

    @When("^I continue shopping$")
    public void continueShopping() {
        cartProductLayer.clickContinueShoppingButton();
    }

    @When("^I select Check out option from Cart dropdown$")
    public void selectCheckoutOptionFromCartDropDown() {
        homePage.selectCheckoutCartOption();
    }
}
