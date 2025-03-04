
//Let's create the RegisterSteps.java file

//src/test/java/com/dsportal/stepdefinitions/RegisterSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.HomePage;
import com.dsportal.pages.RegisterPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterSteps {
 
 private WebDriver driver = WebDriverManager.getDriver();
 private RegisterPage registerPage = new RegisterPage(driver);
 private HomePage homePage = new HomePage(driver);
 
 @Given("user is on register page")
 public void userIsOnRegisterPage() {
     driver.get(Constants.REGISTER_URL);
 }
 
 @When("user enters valid username {string}")
 public void userEntersValidUsername(String username) {
     registerPage.enterUsername(username);
 }
 
 @When("user enters existing username {string}")
 public void userEntersExistingUsername(String username) {
     registerPage.enterUsername(username);
 }
 
 @When("user enters valid password {string}")
 public void userEntersValidPassword(String password) {
     registerPage.enterPassword(password);
 }
 
 @When("user enters valid password confirmation {string}")
 public void userEntersValidPasswordConfirmation(String confirmPassword) {
     registerPage.enterConfirmPassword(confirmPassword);
 }
 
 @When("user enters different password confirmation {string}")
 public void userEntersDifferentPasswordConfirmation(String confirmPassword) {
     registerPage.enterConfirmPassword(confirmPassword);
 }
 
 @When("user clicks on register button")
 public void userClicksOnRegisterButton() {
     registerPage.clickRegister();
 }
 
 @Then("user should be registered successfully")
 public void userShouldBeRegisteredSuccessfully() {
     Assert.assertTrue(homePage.isAlertMessageDisplayed());
     Assert.assertTrue(homePage.getAlertMessage().contains(Constants.REGISTER_SUCCESS_MESSAGE));
 }
 
 @Then("error message about existing user should be displayed")
 public void errorMessageAboutExistingUserShouldBeDisplayed() {
     Assert.assertTrue(registerPage.isErrorMessageDisplayed());
     Assert.assertTrue(registerPage.getErrorMessage().contains("already exists"));
 }
 
 @Then("error message about password mismatch should be displayed")
 public void errorMessageAboutPasswordMismatchShouldBeDisplayed() {
     Assert.assertTrue(registerPage.isErrorMessageDisplayed());
     Assert.assertTrue(registerPage.getErrorMessage().contains("do not match"));
 }
}