

//Register Page class
//src/main/java/com/dsportal/pages/RegisterPage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
 
 @FindBy(id = "id_username")
 private WebElement usernameField;
 
 @FindBy(id = "id_password1")
 private WebElement passwordField;
 
 @FindBy(id = "id_password2")
 private WebElement confirmPasswordField;
 
 @FindBy(xpath = "//input[@value='Register']")
 private WebElement registerButton;
 
 @FindBy(xpath = "//a[text()='Login ']")
 private WebElement loginLink;
 
 public RegisterPage(WebDriver driver) {
     super(driver);
 }
 
 public void enterUsername(String username) {
     usernameField.clear();
     usernameField.sendKeys(username);
 }
 
 public void enterPassword(String password) {
     passwordField.clear();
     passwordField.sendKeys(password);
 }
 
 public void enterConfirmPassword(String confirmPassword) {
     confirmPasswordField.clear();
     confirmPasswordField.sendKeys(confirmPassword);
 }
 
 public void clickRegister() {
     registerButton.click();
 }
 
 public void register(String username, String password, String confirmPassword) {
     enterUsername(username);
     enterPassword(password);
     enterConfirmPassword(confirmPassword);
     clickRegister();
 }
 
 public void clickLoginLink() {
     loginLink.click();
 }
 
 public boolean isErrorMessageDisplayed() {
     return isElementPresent(By.xpath("//div[contains(@class, 'alert-danger')]"));
 }
 
 public String getErrorMessage() {
     return getText(By.xpath("//div[contains(@class, 'alert-danger')]"));
 }
}