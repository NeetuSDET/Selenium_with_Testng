
//Now let's create DataStructurePage.java for data structure specific operations

//src/main/java/com/dsportal/pages/DataStructurePage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DataStructurePage extends BasePage {
 
 @FindBy(xpath = "//a[contains(text(), 'Try here')]")
 private WebElement tryHereButton;
 
 @FindBy(xpath = "//div[@class='CodeMirror-scroll']")
 private WebElement codeEditorContainer;
 
 @FindBy(xpath = "//textarea[@autocorrect='off']")
 private WebElement codeTextArea;
 
 @FindBy(xpath = "//button[text()='Run']")
 private WebElement runButton;
 
 @FindBy(id = "output")
 private WebElement outputContainer;
 
 public DataStructurePage(WebDriver driver) {
     super(driver);
 }
 
 public void clickOnTopicLink(String topicName) {
     By topicLocator = By.xpath("//a[contains(text(), '" + topicName + "')]");
     click(topicLocator);
 }
 
 public void clickTryHereButton() {
     tryHereButton.click();
 }
 
 public void enterCode(String code) {
     waitForElementVisible(By.xpath("//div[@class='CodeMirror-scroll']"));
     ((JavascriptExecutor) driver).executeScript(
         "arguments[0].CodeMirror.setValue(arguments[1]);", 
         driver.findElement(By.className("CodeMirror")), 
         code
     );
 }
 
 public void clickRunButton() {
     runButton.click();
 }
 
 public String getOutput() {
     return outputContainer.getText().trim();
 }
 
 public boolean isAlertPresent() {
     try {
         driver.switchTo().alert();
         return true;
     } catch (Exception e) {
         return false;
     }
 }
 
 public String getAlertText() {
     return driver.switchTo().alert().getText();
 }
 
 public void acceptAlert() {
     driver.switchTo().alert().accept();
 }
 
 public List<WebElement> getAllTopicLinks() {
     return driver.findElements(By.xpath("//a[contains(@href, '/') and not(contains(@href, 'login'))]"));
 }
 
 public boolean isPageContainingText(String text) {
     return driver.getPageSource().contains(text);
 }
}
