#// src/test/resources/features/Register.feature
Feature: User is on Home Page and Clicks on Register

  Scenario: User is on Home Page and Clicks on Register link
    Given user is on Home Page
    When user clicks on register link
    Then user redirected to register page
    
  Scenario: User registers with valid information
    Given user is on register page
    When user enters valid username "newuser"
    And user enters valid password "password123"
    And user enters valid password confirmation "password123"
    And user clicks on register button
    Then user should be redirected to the home page
    And user should be registered successfully
    
  Scenario: User registers with existing username
    Given user is on register page
    When user enters existing username "existinguser"
    And user enters valid password "password123"
    And user enters valid password confirmation "password123"
    And user clicks on register button
    Then error message about existing user should be displayed
    
  Scenario: User registers with mismatched passwords
    Given user is on register page
    When user enters valid username "newuser"
    And user enters valid password "password123"
    And user enters different password confirmation "password456"
    And user clicks on register button
    Then error message about password mismatch should be displayed
