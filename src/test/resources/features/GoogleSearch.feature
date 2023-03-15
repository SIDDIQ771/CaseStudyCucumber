Feature: CaseStudyCucumber

  Scenario: Adding item to cart
    Given user login is done
    When user selects the item
         | cart | items |
         | Phones | Nokia Lumia 1520 |
         | Laptops | MacBook air |
         | Phones | Nexus 6 |
         | Monitors | Apple Monitor 24 |
         
    Then Should add item to the cart

  Scenario Outline: Deleting an item from cart
    Given The List of Items is Displayed
    When The Delete button is clicked
    Then Should delete that item

   Scenario Outline: Placing the Order
    Given The Items were selected 
    When the cart items were clicked for purchasing
    Then Should place the order