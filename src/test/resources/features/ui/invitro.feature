@ui
Feature: Invitro tests

  Scenario: Test medical services menu
    Given open the Radiology page
    When click through all the items in the medical services menu
    Then All menu items should be accessible

  Scenario Outline: Change city to "<NewCity>"
    Given open the Invitro main page
    When change the city to "<NewCity>"
    Then the city should be changed to "<NewCity>"
    Examples:
      | NewCity |
      | Омск    |
#
#  Scenario: Get test results
#    Given I open the Invitro website
#    When I try to get test results without filling the fields
#    Then The fields should be highlighted in red with warnings
#    When I fill in the fields with INZ code "231231231", birth date "11.12.2000", and surname "тест"
#    Then The fields should contain the correct values
#
#  Scenario: Compare product prices
#    Given I open the Invitro website
#    When I remember the product price on the analysis page
#    And I add the product to the cart
#    Then The price in the cart should be compared with the remembered price
#    And Print a message if the price is greater or less than 10000
#    And Fail the test if the price is equal to 10000