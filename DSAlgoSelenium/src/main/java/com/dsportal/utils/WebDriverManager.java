
//src/main/java/com/dsportal/utils/WebDriverManager.java
package com.dsportal.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverManager {
 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
 
 public static WebDriver getDriver() {
     if (driver.get() == null) {
         initializeDriver();
     }
     return driver.get();
 }
 
 public static void initializeDriver() {
     String browser = ConfigReader.getBrowser().toLowerCase();
     WebDriver webDriver;
     
     switch (browser) {
         case "chrome":
             io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
             webDriver = new ChromeDriver();
             break;
         case "firefox":
             io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
             webDriver = new FirefoxDriver();
             break;
         case "edge":
             io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
             webDriver = new EdgeDriver();
             break;
         case "safari":
             webDriver = new SafariDriver();
             break;
         default:
             throw new RuntimeException("Unsupported browser: " + browser);
     }
     
     webDriver.manage().window().maximize();
     webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
     webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
     
     driver.set(webDriver);
 }
 
 public static void quitDriver() {
     if (driver.get() != null) {
         driver.get().quit();
         driver.remove();
     }
 }
}
