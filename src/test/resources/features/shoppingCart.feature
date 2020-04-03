@ShoppingCart
Feature: Shopping cart

  Scenario: Add and remove products from shopping cart
    Given I navigate to the Home page
    When I go to Best Sellers section
    Then the product "Printed Chiffon Dress" should have a discount of "-20%"
    When I add the product "Printed Chiffon Dress" to cart
      And I continue shopping
      And I add the product "Blouse" to cart
      And I continue shopping
      And I select Check out option from Cart dropdown
    Then the following product information should be displayed in Shopping Cart page
      | Product Name          | Product Availability |
      | Printed Chiffon Dress | In stock             |
      | Blouse                | In stock             |
    When I remove the product "Blouse" from cart
    Then the product "Blouse" is no longer displayed in Shopping Cart page
      And the total price should be "$18.40"