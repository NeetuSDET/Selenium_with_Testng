
//Home Page class
//src/main/java/com/dsportal/pages/HomePage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
 
 @FindBy(xpath = "//button[text()='Get Started']")
 private WebElement getStartedButton;
 
 @FindBy(xpath = "//a[contains(text(), 'Sign in')]")
 private WebElement signInLink;
 
 @FindBy(xpath = "//a[contains(text(), 'Register')]")
 private WebElement registerLink;
 
 @FindBy(xpath = "//div[contains(@class, 'card')]//a[text()='Get Started']")
 private List<WebElement> dataStructureCards;
 
 private final By dataStructureDropdown = By.xpath("//a[@class='nav-link dropdown-toggle' and contains(text(), 'Data Structures')]");
 private final By arrayOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Arrays']");
 private final By linkedListOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Linked List']");
 private final By stackOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Stack']");
 private final By queueOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Queue']");
 private final By treeOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Tree']");
 private final By graphOption = By.xpath("//div[@class='dropdown-menu']//a[text()='Graph']");
 
 public HomePage(WebDriver driver) {
     super(driver);
 }
 
 public void clickGetStarted() {
     getStartedButton.click();
 }
 
 public void clickSignIn() {
     signInLink.click();
 }
 
 public void clickRegister() {
     registerLink.click();
 }
 
 public void openDataStructureDropdown() {
     click(dataStructureDropdown);
 }
 
 public void selectArrayFromDropdown() {
     openDataStructureDropdown();
     click(arrayOption);
 }
 
 public void selectLinkedListFromDropdown() {
     openDataStructureDropdown();
     click(linkedListOption);
 }
 
 public void selectStackFromDropdown() {
     openDataStructureDropdown();
     click(stackOption);
 }
 
 public void selectQueueFromDropdown() {
     openDataStructureDropdown();
     click(queueOption);
 }
 
 public void selectTreeFromDropdown() {
     openDataStructureDropdown();
     click(treeOption);
 }
 
 public void selectGraphFromDropdown() {
     openDataStructureDropdown();
     click(graphOption);
 }
 
 public void clickGetStartedForDataStructure(String dataStructure) {
     for (WebElement card : dataStructureCards) {
         if (card.findElement(By.xpath("./preceding-sibling::div[contains(@class, 'card-title')]"))
                 .getText().trim().equalsIgnoreCase(dataStructure)) {
             scrollToElement(card);
             card.click();
             break;
         }
     }
 }
 
 public boolean isUserLoggedIn() {
     return isElementPresent(By.xpath("//a[contains(text(), 'Sign out')]"));
 }
 
 public boolean isUserLoggedOut() {
     return isElementPresent(By.xpath("//a[contains(text(), 'Sign in')]"));
 }
 
 public boolean isAlertMessageDisplayed() {
     return isElementPresent(By.xpath("//div[contains(@class, 'alert')]"));
 }
 
 public String getAlertMessage() {
     return getText(By.xpath("//div[contains(@class, 'alert')]"));
 }
}
