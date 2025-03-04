
//Let's create the LoginSteps.java file

//src/test/java/com/dsportal/stepdefinitions/LoginSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.HomePage;
import com.dsportal.pages.LoginPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
 
 private WebDriver driver = WebDriverManager.getDriver();
 private LoginPage loginPage = new LoginPage(driver);
 private HomePage homePage = new HomePage(driver);
 
 @Given("user is on login page")
 public void userIsOnLoginPage() {
     driver.get(Constants.LOGIN_URL);
 }
 
 @When("user enters valid username {string} and password {string}")
 public void userEntersValidUsernameAndPassword(String username, String password) {
     loginPage.enterUsername(username);
     loginPage.enterPassword(password);
 }
 
 @When("user enters invalid username {string} and password {string}")
 public void userEntersInvalidUsernameAndPassword(String username, String password) {
     loginPage.enterUsername(username);
     loginPage.enterPassword(password);
 }
 
 @When("user enters username {string} and password {string}")
 public void userEntersUsernameAndPassword(String username, String password) {
     if (username.equalsIgnoreCase("empty")) {
         loginPage.enterUsername("");
     } else {
         loginPage.enterUsername(username);
     }
     
     if (password.equalsIgnoreCase("empty")) {
         loginPage.enterPassword("");
     } else {
         loginPage.enterPassword(password);
     }
 }
 
 @When("user clicks on login button")
 public void userClicksOnLoginButton() {
     loginPage.clickLogin();
 }
 
 @Then("user should be redirected to the home page")
 public void userShouldBeRedirectedToTheHomePage() {
     Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
 }
 
 @Then("user should be logged in successfully")
 public void userShouldBeLoggedInSuccessfully() {
     Assert.assertTrue(homePage.isUserLoggedIn());
 }
 
 @Then("error message should be displayed")
 public void errorMessageShouldBeDisplayed() {
     Assert.assertTrue(loginPage.isErrorMessageDisplayed());
 }
 
 @Then("appropriate message should be displayed")
 public void appropriateMessageShouldBeDisplayed() {
     if (loginPage.isErrorMessageDisplayed()) {
         System.out.println("Error message: " + loginPage.getErrorMessage());
     } else if (homePage.isAlertMessageDisplayed()) {
         System.out.println("Alert message: " + homePage.getAlertMessage());
     }
 }
}
