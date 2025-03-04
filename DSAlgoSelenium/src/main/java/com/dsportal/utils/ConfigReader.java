//Now let's create configuration utility classes

//src/main/java/com/dsportal/utils/ConfigReader.java
package com.dsportal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
 private static Properties properties;
 private static final String PROPERTY_FILE_PATH = "src/main/resources/config.properties";/////?
 
 static {
     try {
         properties = new Properties();
         FileInputStream input = new FileInputStream(PROPERTY_FILE_PATH);
         properties.load(input);
         input.close();
     } catch (IOException e) {
         e.printStackTrace();
         throw new RuntimeException("Failed to load config.properties file");
     }
 }
 
 public static String getProperty(String key) {
     return properties.getProperty(key);
 }
 
 public static String getBrowser() {
     return getProperty("browser");
 }
 
 public static String getUrl() {
     return getProperty("url");
 }
 
 public static String getUsername() {
     return getProperty("username");
 }
 
 public static String getPassword() {
     return getProperty("password");
 }
 
 public static int getImplicitWait() {
     return Integer.parseInt(getProperty("implicitWait"));
 }
 
 public static int getExplicitWait() {
     return Integer.parseInt(getProperty("explicitWait"));
 }
}