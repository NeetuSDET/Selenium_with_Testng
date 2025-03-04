
//src/test/java/com/dsportal/stepdefinitions/Hooks.java

package com.dsportal.stepdefinitions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
 
 @BeforeMethod
public void setup() {
     WebDriverManager.initializeDriver();
 }
 
 @AfterMethod
@AfterMethod
public void tearDown(Scenario scenario) {
     WebDriver driver = WebDriverManager.getDriver();
     
     if (scenario.isFailed()) {
         // Take screenshot if scenario fails
         byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
         scenario.attach(screenshot, "image/png", "Screenshot of failure");
     }
     
     WebDriverManager.quitDriver();
 }
}