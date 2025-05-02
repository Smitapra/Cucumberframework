Feature: Login functionality

  Background:
    Given user should be on login page


  @SS
  Scenario: TC01_valid_login_page
    When user enter the valid credential and click to login button
    Then user should be navigated to Home page
    And user can see the logout link
    And user able to logout

  @SS
  Scenario: TC02_invalid_login_page
    When user enter the invalid credential and click to login button
    Then user can see the error message




      @Datadriven
  Scenario Outline: Invalid Login Page with different data
    When user enter the invalid credential username as "<userid>" and password as "<password>"
    And click on login button
    Then user can see the error message
    And Close the Browser
    Examples:
    |userid | password|
    |ad1    | pwd1    |
    |ad2    | pwd2    |
    |ad3    | pwd3    |


