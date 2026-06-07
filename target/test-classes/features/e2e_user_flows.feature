Feature: Swag Labs E-Commerce End-to-End Validation

  Background:
    Given the user navigates to the Swag Labs landing portal

  Scenario: Verify application handles successful authentication mapping
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks on the authentication submission button
    Then the user should see the central catalog dashboard page titled "Swag Labs"
    And the product catalog grid display panel should be visible

  Scenario: Verify application restricts access to locked out accounts
    When the user enters username "locked_out_user" and password "secret_sauce"
    And clicks on the authentication submission button
    Then a critical error dialog should present "Sorry, this user has been locked out."

  Scenario: Complete full transactional checkout pipeline flow
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks on the authentication submission button
    And the user appends the first catalog item into the active cart
    And navigates directly into the cart overview portal
    Then the interactive shopping cart badge indicator should display "1"

  Scenario: Verify product sorting behavior by price from low to high
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks on the authentication submission button
    And the user selects sorting option "Price (low to high)"
    Then the items should be arranged with the cheapest product appearing first

  Scenario: Verify dynamic cart state when removing an item
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks on the authentication submission button
    And the user appends the first catalog item into the active cart
    And the user removes that item from the cart
    Then the shopping cart badge indicator should no longer be displayed