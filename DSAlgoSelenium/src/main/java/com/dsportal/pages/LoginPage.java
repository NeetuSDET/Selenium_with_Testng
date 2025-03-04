
//Login Page class
//src/main/java/com/dsportal/pages/LoginPage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
 
 @FindBy(id = "id_username")
 private WebElement usernameField;
 
 @FindBy(id = "id_password")
 private WebElement passwordField;
 
 @FindBy(xpath = "//input[@value='Login']")
 private WebElement loginButton;
 
 @FindBy(xpath = "//a[text()='Register!']")
 private WebElement registerLink;
 
 public LoginPage(WebDriver driver) {
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
 
 public void clickLogin() {
     loginButton.click();
 }
 
 public void login(String username, String password) {
     enterUsername(username);
     enterPassword(password);
     clickLogin();
 }
 
 public void clickRegisterLink() {
     registerLink.click();
 }
 
 public boolean isErrorMessageDisplayed() {
     return isElementPresent(By.xpath("//div[contains(@class, 'alert-danger')]"));
 }
 
 public String getErrorMessage() {
     return getText(By.xpath("//div[contains(@class, 'alert-danger')]"));
 }
}