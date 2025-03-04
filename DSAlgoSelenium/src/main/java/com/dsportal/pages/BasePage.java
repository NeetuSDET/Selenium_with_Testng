
//Now let's create the base page class that all page objects will extend

//src/main/java/com/dsportal/pages/BasePage.java

package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
 protected WebDriver driver;
 protected WebDriverWait wait;
 
 public BasePage(WebDriver driver) {
     this.driver = driver;
     this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     PageFactory.initElements(driver, this);
 }
 
 protected WebElement waitForElementVisible(By locator) {
     return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }
 
 protected WebElement waitForElementClickable(By locator) {
     return wait.until(ExpectedConditions.elementToBeClickable(locator));
 }
 
 protected boolean isElementPresent(By locator) {
     try {
         driver.findElement(locator);
         return true;
     } catch (Exception e) {
         return false;
     }
 }
 
 protected void click(By locator) {
     waitForElementClickable(locator).click();
 }
 
 protected void type(By locator, String text) {
     WebElement element = waitForElementVisible(locator);
     element.clear();
     element.sendKeys(text);
 }
 
 protected String getText(By locator) {
     return waitForElementVisible(locator).getText();
 }
 
 protected List<WebElement> getElements(By locator) {
     return driver.findElements(locator);
 }
 
 protected void scrollToElement(WebElement element) {
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
 }
 
 protected void waitForPageLoad() {
     wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
 }
 
 public String getPageTitle() {
     return driver.getTitle();
 }
 
 public String getCurrentUrl() {
     return driver.getCurrentUrl();
 }
}