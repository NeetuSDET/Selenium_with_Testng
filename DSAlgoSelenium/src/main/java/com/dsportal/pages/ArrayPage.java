
//Let's create specific page classes for each data structure

//src/main/java/com/dsportal/pages/ArrayPage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArrayPage extends DataStructurePage {
 
 @FindBy(xpath = "//h4[text()='Array']/..//a[text()='Get Started']")
 private WebElement arrayGetStartedButton;
 
 @FindBy(xpath = "//a[contains(text(), 'Arrays in Python')]")
 private WebElement arraysInPythonLink;
 
 @FindBy(xpath = "//a[contains(text(), 'Arrays Using List')]")
 private WebElement arraysUsingListLink;
 
 @FindBy(xpath = "//a[contains(text(), 'Basic Operations in Lists')]")
 private WebElement basicOperationsInListsLink;
 
 @FindBy(xpath = "//a[contains(text(), 'Applications of Array')]")
 private WebElement applicationsOfArrayLink;
 
 private final By practiceQuestionsLink = By.xpath("//a[contains(text(), 'Practice Questions')]");
 
 public ArrayPage(WebDriver driver) {
     super(driver);
 }
 
 public void clickArrayGetStartedButton() {
     arrayGetStartedButton.click();
 }
 
 public void clickArraysInPythonLink() {
     arraysInPythonLink.click();
 }
 
 public void clickArraysUsingListLink() {
     arraysUsingListLink.click();
 }
 
 public void clickBasicOperationsInListsLink() {
     basicOperationsInListsLink.click();
 }
 
 public void clickApplicationsOfArrayLink() {
     applicationsOfArrayLink.click();
 }
 
 public void clickPracticeQuestionsLink() {
     click(practiceQuestionsLink);
 }
 
 public List<WebElement> getPracticeQuestions() {
     return getElements(By.xpath("//div[@class='list-group']/a"));
 }
 
 public void clickOnPracticeQuestion(int index) {
     List<WebElement> questions = getPracticeQuestions();
     if (index >= 0 && index < questions.size()) {
         questions.get(index).click();
     } else {
         throw new IndexOutOfBoundsException("Invalid practice question index: " + index);
     }
 }
}
