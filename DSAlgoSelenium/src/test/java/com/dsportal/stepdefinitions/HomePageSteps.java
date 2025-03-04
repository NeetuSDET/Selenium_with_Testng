
//Now let's create a step definition class for the Home feature

//src/test/java/com/dsportal/stepdefinitions/HomePageSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.HomePage;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePageSteps {
 
 private WebDriver driver = WebDriverManager.getDriver();
 private HomePage homePage = new HomePage(driver);
 
 @Given("user opens the dsalgo portal link")
 public void userOpensTheDsalgoPortalLink() {
     driver.get("https://dsportalapp.herokuapp.com/");
 }
 
 @When("user clicks on \"Get Started\" button")
 public void userClicksOnGetStartedButton() {
     homePage.clickGetStarted();
 }
 
 @Then("user redirected to home page")
 public void userRedirectedToHomePage() {
     Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
 }
 
 @Given("user in home page")
 public void userInHomePage() {
     driver.get("https://dsportalapp.herokuapp.com/home");
 }
 
 @When("user clicks on data structure dropdown before signin")
 public void userClicksOnDataStructureDropdownBeforeSignin() {
     homePage.openDataStructureDropdown();
 }
 
 @Then("user select any dropdown menu {string}")
 public void userSelectAnyDropdownMenu(String option) {
     switch (option.trim()) {
         case "Arrays":
             homePage.selectArrayFromDropdown();
             break;
         case "Linked List":
             homePage.selectLinkedListFromDropdown();
             break;
         case "Stack":
             homePage.selectStackFromDropdown();
             break;
         case "Queue":
             homePage.selectQueueFromDropdown();
             break;
         case "Tree":
             homePage.selectTreeFromDropdown();
             break;
         case "Graph":
             homePage.selectGraphFromDropdown();
             break;
         default:
             Assert.fail("Invalid option: " + option);
     }
 }
 
 @Then("it should alert the user with the message {string}")
 public void itShouldAlertTheUserWithTheMessage(String message) {
     Assert.assertTrue(homePage.isAlertMessageDisplayed());
     Assert.assertEquals(homePage.getAlertMessage(), message);
 }
 
 @When("user click any of the Get started link before signin {string}")
 public void userClickAnyOfTheGetStartedLinkBeforeSignin(String dataStructure) {
     homePage.clickGetStartedForDataStructure(dataStructure);
 }
}