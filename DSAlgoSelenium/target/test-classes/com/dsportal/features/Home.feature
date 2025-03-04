#// Now let's create some example feature files
#
#// src/test/resources/features/Home.feature
Feature: Launch Start page and Test Home Page

  Scenario: Launch the home page of dsalgo portal
    Given user opens the dsalgo portal link
    When user clicks on "Get Started" button
    Then user redirected to home page

  Scenario Outline: User is on Home Page and clicks on any dropdown "<value>" without sign in
    Given user in home page
    When user clicks on data structure dropdown before signin
    Then user select any dropdown menu "<value>"
    Then it should alert the user with the message "You are not logged in"

    Examples: 
      | value       |
      | Arrays      |
      | Linked List |
      | Stack       |
      | Queue       |
      | Tree        |
      | Graph       |

  Scenario Outline: User is on Home Page and clicks on any getStarted link "<value>" without sign in
    Given user in home page
    When user click any of the Get started link before signin "<value>"
    Then it should alert the user with the message "You are not logged in"

    Examples: 
      | value                        |
      | Data Structures-Introduction |
      | Arrays                       |
      | Linked List                  |
      | Stack                        |
      | Queue                        |
      | Tree                         |
      | Graph                        |
