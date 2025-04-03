Feature: Lead Functionality

  Background:
    Given user should be on login page
    When user enter the valid credential and click to login button

    @SS
    Scenario: TC03_lead_creation
      When user click on the new lead link
      And fill the lastname and company name and click to save button
      Then Lead should be created and validated successfully
      And Close the Browser


