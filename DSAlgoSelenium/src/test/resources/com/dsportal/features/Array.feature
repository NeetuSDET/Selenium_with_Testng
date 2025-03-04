Feature: Array Page Functionality
  As a user of DS Portal App
  I want to use the Array data structure features
  So that I can learn and practice array operations

  Background:
    Given I am on the DSPortalApp homepage "https://dsportalapp.herokuapp.com/"
    And I click on "Get Started" button
    And I click on "Sign in" link
    And I login with username "March2025@dsalgo" and password "Neetuprajna"
    And I navigate to Array data structure page

  Scenario: Accessing Array Data Structure Introduction Page
    When I click on "Arrays in Python" link
    Then I should be redirected to the Arrays introduction page
    And I should see the title "Arrays in Python"
    And I should see introduction content about arrays

  Scenario: Navigating to Array Practice Questions
    When I click on "Arrays Using List" link
    Then I should be redirected to the arrays using list page
    And I should see the title "Arrays Using List"
    And I should see a "Try here" button
    When I click on "Try here" button
    Then I should be redirected to a page with a code editor
    And I should be able to write Python code in the editor
    And I should see a "Run" button to execute the code

  Scenario: Running Valid Python Array Code
    Given I am on the "Arrays Using List" page
    When I click on "Try here" button
    And I enter valid Python array code:
      """
      my_array = [1, 2, 3, 4, 5]
      print(my_array)
      print("Length of array:", len(my_array))
      print("First element:", my_array[0])
      print("Last element:", my_array[-1])
      """
    And I click the "Run" button
    Then I should see the output showing the array and its properties

  Scenario: Running Invalid Python Array Code
    Given I am on the "Arrays Using List" page
    When I click on "Try here" button
    And I enter invalid Python array code:
      """
      my_array = [1, 2, 3
      print(my_array)
      """
    And I click the "Run" button
    Then I should see a syntax error message

  Scenario: Accessing Basic Operations in Array
    When I click on "Basic Operations in Lists" link
    Then I should be redirected to the basic operations page
    And I should see the title "Basic Operations in Lists"
    And I should see content about basic array operations
    And I should see a "Try here" button

  Scenario: Accessing Applications of Array
    When I click on "Applications of Array" link
    Then I should be redirected to the applications page
    And I should see the title "Applications of Array"
    And I should see content about array applications
    And I should see a "Try here" button

  Scenario: Practice Questions Navigation
    When I click on "Practice Questions" link
    Then I should be redirected to the practice questions page
    And I should see a list of array practice questions
    And I should be able to select a question to solve

  Scenario: Solving Array Practice Question
    Given I am on the array practice questions page
    When I click on the first practice question
    Then I should be redirected to the question page
    When I enter a valid solution in the code editor
    And I click the "Run" button
    Then I should see the correct output
    When I click the "Submit" button
    Then I should see a success message

  Scenario: Sign Out Functionality
    When I click on "Sign out" link in the navigation menu
    Then I should be logged out successfully
    And I should be redirected to the homepage