package com.dharbor.serenity.steps;

import com.dharbor.ui.pages.ShoppingCartPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ShoppingCartSteps {

    //Pages
    private ShoppingCartPage shoppingCartPage;

    @Then("^the following product information should be displayed in Shopping Cart page$")
    public void navigateToHomePage(DataTable productInformationTable) {
        List<Map<String, String>> listOfProducts = productInformationTable.asMaps(String.class, String.class);
        shoppingCartPage = new ShoppingCartPage();

        for (Map <String, String> product: listOfProducts) {
            String productName = product.get("Product Name");
            String productAvailability = product.get("Product Availability");
            assertTrue(shoppingCartPage.isProductDisplayed(productName), "The product: "
                    + productName + " was not displayed.");
            assertTrue(shoppingCartPage.isProductAvailabilityDisplayed(productName, productAvailability),
                    "The product availability of: " + productName + " wasn't displayed.");
        }
    }

    @When("^I remove the product \"(.*?)\" from cart$")
    public void removeTheProductFromCart(String productName) {
        shoppingCartPage.removeProductFromCart(productName);
    }

    @Then("^the product \"(.*?)\" is no longer displayed in Shopping Cart page$")
    public void verifyProductIsNotDisplayed(String productName) {
        assertFalse(shoppingCartPage.isProductDisplayed(productName),
                "The product: " + productName + " was displayed.");
    }

    @Then("^the total price should be \"(.*?)\"$")
    public void verifyTotalPrice(String expectedTotalPrice) {
        String actualTotalPrice = shoppingCartPage.getTotalPrice();
        assertEquals(actualTotalPrice, expectedTotalPrice, "Expected total price didn't match");
    }
}
