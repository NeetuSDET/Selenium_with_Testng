

//Let's create the Test Runner

//src/test/java/com/dsportal/runners/TestRunner.java
package com.dsportal.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
@RunWith(Cucumber.class) 
@CucumberOptions(
 features = "src/test/resources/features/Home.feature",
 glue = {"com.dsportal.stepdefinitions"},
 plugin = {
     "pretty",
     "html:target/cucumber-reports/cucumber-pretty.html",
     "json:target/cucumber-reports/CucumberTestReport.json",
     "rerun:target/cucumber-reports/rerun.txt"
 },
 monochrome = true,
 dryRun = false,
 tags = "@Smoke or @Regression"
)
public class TestRunner{
//public class TestRunner extends AbstractTestNGCucumberTests {
// @Override
// @DataProvider(parallel = true)
// public Object[][] scenarios() {
//     return super.scenarios();
// }
}


//@RunWith(Cucumber.class) @CucumberOptions( features = "src/test/resources/features/Home.feature", glue = {"com.dsportal.stepdefinitions"}, plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty.html", "json:target/cucumber-reports/CucumberTestReport.json", "rerun:target/cucumber-reports/rerun.txt" }, monochrome = true, dryRun = false, tags = "@Smoke or @Regression" ) public class TestRunner { 

