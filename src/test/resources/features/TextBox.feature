Feature: TextBox Page Functionality

  Scenario: Verify user can submit text box form successfully

    Given User opens Text Box page
    When User enters full name "Rishabh Gupta"
    And User enters email "rishabh@test.com"
    And User enters current address "Gurugram"
    And User enters permanent address "Delhi"
    And User clicks Submit button
    Then User details output should be displayed successfully