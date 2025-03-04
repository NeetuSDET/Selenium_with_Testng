
#// src/test/resources/features/Login.feature
Feature: User is on Home Page and Clicks on Signin

  Scenario: User is on Home Page and Clicks on Signin link
    Given user is on Home Page
    When user clicks on signin link
    Then user redirected to login page
    
  Scenario: User login with valid credentials
    Given user is on login page
    When user enters valid username "username" and password "password"
    And user clicks on login button
    Then user should be redirected to the home page
    And user should be logged in successfully
    
  Scenario: User login with invalid credentials
    Given user is on login page
    When user enters invalid username "invalid" and password "invalid"
    And user clicks on login button
    Then error message should be displayed
    
  Scenario Outline: User login with different combinations
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    And user clicks on login button
    Then appropriate message should be displayed
    
    Examples:
      | username | password |
      | valid    | valid    |
      | valid    | invalid  |
      | invalid  | valid    |
      | empty    | valid    |
      | valid    | empty    |
      | empty    | empty    |
