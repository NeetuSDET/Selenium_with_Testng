#// Let's also create a feature file for DataStructure

#// src/test/resources/features/DataStructure.feature
Feature: Data Structure Introduction

  Scenario: User logs in and navigates to Data Structure Introduction page
    Given user is logged in
    When user clicks on Get Started button for "Data Structures-Introduction"
    Then user should be redirected to Data Structure page
    
  Scenario: User navigates through Data Structure topics
    Given user is on Data Structure page
    When user clicks on Time Complexity link
    Then user should be directed to Time Complexity page
    
  Scenario: User tries Python code in Try Editor
    Given user is on Time Complexity page
    When user clicks on Try Here button
    Then user should be redirected to Try Editor page
    When user enters valid Python code
      """
      print("Time Complexity Analysis")
      """
    And user clicks on Run button
    Then the output should be displayed
