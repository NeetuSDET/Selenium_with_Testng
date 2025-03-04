
//src/test/java/com/dsportal/stepdefinitions/DataStructureSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.DataStructurePage;
import com.dsportal.pages.HomePage;
import com.dsportal.pages.LoginPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DataStructureSteps {
 
 private WebDriver driver = WebDriverManager.getDriver();
 private HomePage homePage = new HomePage(driver);
 private LoginPage loginPage = new LoginPage(driver);
 private DataStructurePage dataStructurePage = new DataStructurePage(driver);
 
 @Given("user is logged in")
 public void userIsLoggedIn() {
     driver.get(Constants.LOGIN_URL);
     loginPage.login(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
     Assert.assertTrue(homePage.isUserLoggedIn());
 }
 
 @When("user clicks on Get Started button for {string}")
 public void userClicksOnGetStartedButtonFor(String dataStructure) {
     homePage.clickGetStartedForDataStructure(dataStructure);
 }
 
 @Then("user should be redirected to Data Structure page")
 public void userShouldBeRedirectedToDataStructurePage() {
     Assert.assertTrue(driver.getCurrentUrl().contains("/data-structures-introduction"));
 }
 
 @Given("user is on Data Structure page")
 public void userIsOnDataStructurePage() {
     driver.get(Constants.BASE_URL + "/data-structures-introduction/");
 }
 
 @When("user clicks on Time Complexity link")
 public void userClicksOnTimeComplexityLink() {
     dataStructurePage.clickOnTopicLink("Time Complexity");
 }
 
 @Then("user should be directed to Time Complexity page")
 public void userShouldBeDirectedToTimeComplexityPage() {
     Assert.assertTrue(driver.getCurrentUrl().contains("time-complexity"));
 }
 
 @Given("user is on Time Complexity page")
 public void userIsOnTimeComplexityPage() {
     driver.get(Constants.BASE_URL + "/data-structures-introduction/time-complexity/");
 }
 
 @When("user clicks on Try Here button")
 public void userClicksOnTryHereButton() {
     dataStructurePage.clickTryHereButton();
 }
 
 @Then("user should be redirected to Try Editor page")
 public void userShouldBeRedirectedToTryEditorPage() {
     Assert.assertTrue(driver.getCurrentUrl().contains("/tryEditor"));
 }
 
 @When("user enters valid Python code")
 public void userEntersValidPythonCode(String code) {
     dataStructurePage.enterCode(code);
 }
 
 @When("user clicks on Run button")
 public void userClicksOnRunButton() {
     dataStructurePage.clickRunButton();
 }
 
 @Then("the output should be displayed")
 public void theOutputShouldBeDisplayed() {
     Assert.assertFalse(dataStructurePage.getOutput().isEmpty());
 }
}
