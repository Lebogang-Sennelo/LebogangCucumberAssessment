@login @Regression @Sanity
Feature: login

  Scenario Outline: as a user i want to login to ndosi website
    Given i am on the login page
    And I enter email <email>
    And I enter password <password>
    When I click login button
    Then i should be logged in successfully
    And I click mobile automation
    Examples:
      | email             | password  |
      | admin@gmail.com   | @12345678 |
