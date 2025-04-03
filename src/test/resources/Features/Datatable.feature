Feature: Datatable Concept

  @SSD
  Scenario: datatable implementation
    Given user should be on login page
    When user enter the valid credential and click to login button
    And user need to create multiple lead and validate based on the dataset
    |smita| Synechron |
    |Shambhavi| Capgimini |
    | Harshali | Capgimini |
    | Jyoti    | HSBC      |
    And Close the Browser
