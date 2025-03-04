// src/main/java/com/dsportal/utils/Constants.java (continued)
package com.dsportal.utils;

public class Constants {
    // URLs
    public static final String BASE_URL = "https://dsportalapp.herokuapp.com";
    public static final String HOME_URL = BASE_URL + "/home";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String REGISTER_URL = BASE_URL + "/register";
    
    // Data Structure URLs
    public static final String ARRAY_URL = BASE_URL + "/array/";
    public static final String LINKED_LIST_URL = BASE_URL + "/linked-list/";
    public static final String STACK_URL = BASE_URL + "/stack/";
    public static final String QUEUE_URL = BASE_URL + "/queue/";
    public static final String TREE_URL = BASE_URL + "/tree/";
    public static final String GRAPH_URL = BASE_URL + "/graph/";
    
    // Messages
    public static final String NOT_LOGGED_IN_MESSAGE = "You are not logged in";
    public static final String LOGIN_SUCCESS_MESSAGE = "You are logged in";
    public static final String LOGOUT_SUCCESS_MESSAGE = "Logged out successfully";
    public static final String REGISTER_SUCCESS_MESSAGE = "New Account Created. You are logged in as";
    
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Test Data
    public static final String TEST_USERNAME = "testuser";
    public static final String TEST_PASSWORD = "testpassword";
    public static final String EXCEL_FILE_PATH = "src/test/resources/testdata/dsalgodata.xlsx";
}

// Let's create the Hooks class for setting up and tearing down test runs

// src/test/java/com/dsportal/stepdefinitions/Hooks.java
package com.dsportal.stepdefinitions;

import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    
    @Before
    public void setup() {
        WebDriverManager.initializeDriver();
    }
    
    @After
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

// Let's create the Test Runner

// src/test/java/com/dsportal/runners/TestRunner.java
package com.dsportal.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
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
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

// Let's create the LoginSteps.java file

// src/test/java/com/dsportal/stepdefinitions/LoginSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.HomePage;
import com.dsportal.pages.LoginPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
    
    private WebDriver driver = WebDriverManager.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private HomePage homePage = new HomePage(driver);
    
    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver.get(Constants.LOGIN_URL);
    }
    
    @When("user enters valid username {string} and password {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @When("user enters invalid username {string} and password {string}")
    public void userEntersInvalidUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @When("user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        if (username.equalsIgnoreCase("empty")) {
            loginPage.enterUsername("");
        } else {
            loginPage.enterUsername(username);
        }
        
        if (password.equalsIgnoreCase("empty")) {
            loginPage.enterPassword("");
        } else {
            loginPage.enterPassword(password);
        }
    }
    
    @When("user clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLogin();
    }
    
    @Then("user should be redirected to the home page")
    public void userShouldBeRedirectedToTheHomePage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
    }
    
    @Then("user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(homePage.isUserLoggedIn());
    }
    
    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
    
    @Then("appropriate message should be displayed")
    public void appropriateMessageShouldBeDisplayed() {
        if (loginPage.isErrorMessageDisplayed()) {
            System.out.println("Error message: " + loginPage.getErrorMessage());
        } else if (homePage.isAlertMessageDisplayed()) {
            System.out.println("Alert message: " + homePage.getAlertMessage());
        }
    }
}

// Let's create the RegisterSteps.java file

// src/test/java/com/dsportal/stepdefinitions/RegisterSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.HomePage;
import com.dsportal.pages.RegisterPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterSteps {
    
    private WebDriver driver = WebDriverManager.getDriver();
    private RegisterPage registerPage = new RegisterPage(driver);
    private HomePage homePage = new HomePage(driver);
    
    @Given("user is on register page")
    public void userIsOnRegisterPage() {
        driver.get(Constants.REGISTER_URL);
    }
    
    @When("user enters valid username {string}")
    public void userEntersValidUsername(String username) {
        registerPage.enterUsername(username);
    }
    
    @When("user enters existing username {string}")
    public void userEntersExistingUsername(String username) {
        registerPage.enterUsername(username);
    }
    
    @When("user enters valid password {string}")
    public void userEntersValidPassword(String password) {
        registerPage.enterPassword(password);
    }
    
    @When("user enters valid password confirmation {string}")
    public void userEntersValidPasswordConfirmation(String confirmPassword) {
        registerPage.enterConfirmPassword(confirmPassword);
    }
    
    @When("user enters different password confirmation {string}")
    public void userEntersDifferentPasswordConfirmation(String confirmPassword) {
        registerPage.enterConfirmPassword(confirmPassword);
    }
    
    @When("user clicks on register button")
    public void userClicksOnRegisterButton() {
        registerPage.clickRegister();
    }
    
    @Then("user should be registered successfully")
    public void userShouldBeRegisteredSuccessfully() {
        Assert.assertTrue(homePage.isAlertMessageDisplayed());
        Assert.assertTrue(homePage.getAlertMessage().contains(Constants.REGISTER_SUCCESS_MESSAGE));
    }
    
    @Then("error message about existing user should be displayed")
    public void errorMessageAboutExistingUserShouldBeDisplayed() {
        Assert.assertTrue(registerPage.isErrorMessageDisplayed());
        Assert.assertTrue(registerPage.getErrorMessage().contains("already exists"));
    }
    
    @Then("error message about password mismatch should be displayed")
    public void errorMessageAboutPasswordMismatchShouldBeDisplayed() {
        Assert.assertTrue(registerPage.isErrorMessageDisplayed());
        Assert.assertTrue(registerPage.getErrorMessage().contains("do not match"));
    }
}

// Now let's create DataStructurePage.java for data structure specific operations

// src/main/java/com/dsportal/pages/DataStructurePage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
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

// Let's create specific page classes for each data structure

// src/main/java/com/dsportal/pages/ArrayPage.java
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

// src/main/java/com/dsportal/pages/LinkedListPage.java
package com.dsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedListPage extends DataStructurePage {
    
    @FindBy(xpath = "//h4[text()='Linked List']/..//a[text()='Get Started']")
    private WebElement linkedListGetStartedButton;
    
    @FindBy(xpath = "//a[contains(text(), 'Introduction')]")
    private WebElement introductionLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Creating Linked List')]")
    private WebElement creatingLinkedListLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Types of Linked List')]")
    private WebElement typesOfLinkedListLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Implement Linked List in Python')]")
    private WebElement implementLinkedListLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Traversal')]")
    private WebElement traversalLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Insertion')]")
    private WebElement insertionLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Deletion')]")
    private WebElement deletionLink;
    
    private final By practiceQuestionsLink = By.xpath("//a[contains(text(), 'Practice Questions')]");
    
    public LinkedListPage(WebDriver driver) {
        super(driver);
    }
    
    public void clickLinkedListGetStartedButton() {
        linkedListGetStartedButton.click();
    }
    
    public void clickIntroductionLink() {
        introductionLink.click();
    }
    
    public void clickCreatingLinkedListLink() {
        creatingLinkedListLink.click();
    }
    
    public void clickTypesOfLinkedListLink() {
        typesOfLinkedListLink.click();
    }
    
    public void clickImplementLinkedListLink() {
        implementLinkedListLink.click();
    }
    
    public void clickTraversalLink() {
        traversalLink.click();
    }
    
    public void clickInsertionLink() {
        insertionLink.click();
    }
    
    public void clickDeletionLink() {
        deletionLink.click();
    }
    
    public void clickPracticeQuestionsLink() {
        click(practiceQuestionsLink);
    }
}

// Let's also create a feature file for DataStructure

// src/test/resources/features/DataStructure.feature
Feature: Data Structure Introduction

  Scenario: User logs in and navigates to Data Structure Introduction page
    Given user is logged in
    When user clicks on Get Started button for "Data Structures-Introduction"
    Then user should be redirected to Data Structure page
    
  Scenario: User navigates through Data Structure topics
    Given user is on Data Structure page
    When user clicks on Time Complexity link
    Then user should be directed to Time Complexity page
    
  Scenario: User tries Python code in Try Editor
    Given user is on Time Complexity page
    When user clicks on Try Here button
    Then user should be redirected to Try Editor page
    When user enters valid Python code
      """
      print("Time Complexity Analysis")
      """
    And user clicks on Run button
    Then the output should be displayed

// src/test/java/com/dsportal/stepdefinitions/DataStructureSteps.java
package com.dsportal.stepdefinitions;

import com.dsportal.pages.DataStructurePage;
import com.dsportal.pages.HomePage;
import com.dsportal.pages.LoginPage;
import com.dsportal.utils.Constants;
import com.dsportal.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DataStructureSteps {
    
    private WebDriver driver = WebDriverManager.getDriver();
    private HomePage homePage = new HomePage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private DataStructurePage dataStructurePage = new DataStructurePage(driver);
    
    @Given("user is logged in")
    public void userIsLoggedIn() {
        driver.get(Constants.LOGIN_URL);
        loginPage.login(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
        Assert.assertTrue(homePage.isUserLoggedIn());
    }
    
    @When("user clicks on Get Started button for {string}")
    public void userClicksOnGetStartedButtonFor(String dataStructure) {
        homePage.clickGetStartedForDataStructure(dataStructure);
    }
    
    @Then("user should be redirected to Data Structure page")
    public void userShouldBeRedirectedToDataStructurePage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/data-structures-introduction"));
    }
    
    @Given("user is on Data Structure page")
    public void userIsOnDataStructurePage() {
        driver.get(Constants.BASE_URL + "/data-structures-introduction/");
    }
    
    @When("user clicks on Time Complexity link")
    public void userClicksOnTimeComplexityLink() {
        dataStructurePage.clickOnTopicLink("Time Complexity");
    }
    
    @Then("user should be directed to Time Complexity page")
    public void userShouldBeDirectedToTimeComplexityPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("time-complexity"));
    }
    
    @Given("user is on Time Complexity page")
    public void userIsOnTimeComplexityPage() {
        driver.get(Constants.BASE_URL + "/data-structures-introduction/time-complexity/");
    }
    
    @When("user clicks on Try Here button")
    public void userClicksOnTryHereButton() {
        dataStructurePage.clickTryHereButton();
    }
    
    @Then("user should be redirected to Try Editor page")
    public void userShouldBeRedirectedToTryEditorPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/tryEditor"));
    }
    
    @When("user enters valid Python code")
    public void userEntersValidPythonCode(String code) {
        dataStructurePage.enterCode(code);
    }
    
    @When("user clicks on Run button")
    public void userClicksOnRunButton() {
        dataStructurePage.clickRunButton();
    }
    
    @Then("the output should be displayed")
    public void theOutputShouldBeDisplayed() {
        Assert.assertFalse(dataStructurePage.getOutput().isEmpty());
    }
}

// Let's create config.properties file

// src/main/resources/config.properties
browser=chrome
url=https://dsportalapp.herokuapp.com
username=testuser
password=testpassword
implicitWait=10
explicitWait=20
headless=false
