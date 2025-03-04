package com.dsportal.stepdefinitions;

import org.testng.annotations.AfterMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class ArraySteps  {
    private WebDriver driver;
    private WebDriverWait wait;
    private Map<String, String> pageUrls;

    @BeforeMethod
	public void setup() {
        // Set up WebDriver - you may need to set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Initialize page URLs map
        pageUrls = new HashMap<>();
        pageUrls.put("Arrays Using List", "https://dsportalapp.herokuapp.com/array/arrays-using-list/");
        pageUrls.put("Basic Operations in Lists", "https://dsportalapp.herokuapp.com/array/basic-operations-in-lists/");
        pageUrls.put("Applications of Array", "https://dsportalapp.herokuapp.com/array/applications-of-array/");
    }

    @AfterMethod
	@AfterMethod
	public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the DSPortalApp homepage {string}")
    public void i_am_on_the_dsportalapp_homepage(String url) {
        driver.get(url);
        AssertJUnit.assertTrue("Page title should contain 'NumpyNinja'", driver.getTitle().contains("NumpyNinja"));
    }

    @Given("I click on {string} button")
    @When("I click on {string} button")
    public void i_click_on_button(String buttonText) {
        WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), '" + buttonText + "')]"))
        );
        button.click();
    }

    @Given("I click on {string} link")
    @When("I click on {string} link")
    public void i_click_on_link(String linkText) {
        WebElement link = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + linkText + "')]"))
        );
        link.click();
    }

    @Given("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        WebElement usernameField = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("id_username"))
        );
        WebElement passwordField = driver.findElement(By.id("id_password"));
        
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
        
        // Verify login success
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-primary']"))
        );
    }

    @Given("I navigate to Array data structure page")
    public void i_navigate_to_array_data_structure_page() {
        // Navigate to Data Structures introduction
        WebElement dsGetStarted = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='data-structures-introduction']"))
        );
        dsGetStarted.click();
        
        // Click on Array Get Started button
        WebElement arrayGetStarted = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='array']"))
        );
        arrayGetStarted.click();
    }

    @Then("I should be redirected to the Arrays introduction page")
    public void i_should_be_redirected_to_the_arrays_introduction_page() {
        wait.until(ExpectedConditions.urlContains("array/arrays-in-python"));
    }

    @Then("I should see the title {string}")
    public void i_should_see_the_title(String titleText) {
        WebElement title = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(), '" + titleText + "')]"))
        );
        AssertJUnit.assertTrue("Title should be displayed", title.isDisplayed());
    }

    @Then("I should see introduction content about arrays")
    public void i_should_see_introduction_content_about_arrays() {
        WebElement content = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-sm-12']//p"))
        );
        AssertJUnit.assertTrue("Content should be displayed", content.isDisplayed());
        AssertJUnit.assertTrue("Content should not be empty", content.getText().length() > 0);
    }

    @Then("I should be redirected to the arrays using list page")
    public void i_should_be_redirected_to_the_arrays_using_list_page() {
        wait.until(ExpectedConditions.urlContains("array/arrays-using-list"));
    }

    @Then("I should see a {string} button")
    public void i_should_see_a_button(String buttonText) {
        WebElement button = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), '" + buttonText + "')]"))
        );
        AssertJUnit.assertTrue("Button should be displayed", button.isDisplayed());
    }

    @Then("I should be redirected to a page with a code editor")
    public void i_should_be_redirected_to_a_page_with_a_code_editor() {
        wait.until(ExpectedConditions.urlContains("/tryEditor"));
        
        // Check for code editor
        WebElement editor = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.className("CodeMirror"))
        );
        AssertJUnit.assertTrue("Code editor should be displayed", editor.isDisplayed());
    }

    @Then("I should be able to write Python code in the editor")
    public void i_should_be_able_to_write_python_code_in_the_editor() {
        // CodeMirror is a complex editor, we need to access its internal DOM
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
            "arguments[0].CodeMirror.setValue('print(\"Test Code\")')",
            driver.findElement(By.className("CodeMirror"))
        );
        
        // Get the value to verify it was set
        String value = (String) jsExecutor.executeScript(
            "return arguments[0].CodeMirror.getValue()",
            driver.findElement(By.className("CodeMirror"))
        );
        
        AssertJUnit.assertTrue("Editor should contain the test code", value.contains("Test Code"));
    }

    @Then("I should see a {string} button to execute the code")
    public void i_should_see_a_button_to_execute_the_code(String buttonText) {
        WebElement button = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), '" + buttonText + "')]"))
        );
        AssertJUnit.assertTrue("Button should be displayed", button.isDisplayed());
    }

    @Given("I am on the code editor page after clicking {string}")
    public void i_am_on_the_code_editor_page_after_clicking(String linkText) {
        // Navigate to Arrays Using List page
        driver.get("https://dsportalapp.herokuapp.com/array/arrays-using-list/");
        
        // Click Try here button
        WebElement tryHere = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + linkText + "')]"))
        );
        tryHere.click();
        
        // Verify we're on the editor page
        wait.until(ExpectedConditions.urlContains("/tryEditor"));
    }

    @When("I enter valid Python array code:")
    public void i_enter_valid_python_array_code(String code) {
        // Set the code in CodeMirror
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
            "arguments[0].CodeMirror.setValue(arguments[1])",
            driver.findElement(By.className("CodeMirror")),
            code
        );
    }

    @When("I enter invalid Python array code:")
    public void i_enter_invalid_python_array_code(String code) {
        // Set the code in CodeMirror
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
            "arguments[0].CodeMirror.setValue(arguments[1])",
            driver.findElement(By.className("CodeMirror")),
            code
        );
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" + buttonText + "']"))
        );
        button.click();
        
        // Brief pause to allow execution
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see the output showing the array and its properties")
    public void i_should_see_the_output_showing_the_array_and_its_properties() {
        WebElement output = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        
        String outputText = output.getText();
        AssertJUnit.assertTrue("Output should contain array", outputText.contains("[1, 2, 3, 4, 5]"));
        AssertJUnit.assertTrue("Output should contain length info", outputText.contains("Length of array:"));
        AssertJUnit.assertTrue("Output should contain first element info", outputText.contains("First element:"));
        AssertJUnit.assertTrue("Output should contain last element info", outputText.contains("Last element:"));
    }

    @Then("I should see a syntax error message")
    public void i_should_see_a_syntax_error_message() {
        WebElement output = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        
        String outputText = output.getText();
        AssertJUnit.assertTrue("Output should contain error message", 
                  outputText.contains("SyntaxError") || outputText.contains("Error"));
    }

    @Then("I should be redirected to the basic operations page")
    public void i_should_be_redirected_to_the_basic_operations_page() {
        wait.until(ExpectedConditions.urlContains("array/basic-operations-in-lists"));
    }

    @Then("I should see content about basic array operations")
    public void i_should_see_content_about_basic_array_operations() {
        WebElement content = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-sm-12']//p"))
        );
        AssertJUnit.assertTrue("Content should be displayed", content.isDisplayed());
        AssertJUnit.assertTrue("Content should not be empty", content.getText().length() > 0);
    }

    @Then("I should be redirected to the applications page")
    public void i_should_be_redirected_to_the_applications_page() {
        wait.until(ExpectedConditions.urlContains("array/applications-of-array"));
    }

    @Then("I should see content about array applications")
    public void i_should_see_content_about_array_applications() {
        WebElement content = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-sm-12']//p"))
        );
        AssertJUnit.assertTrue("Content should be displayed", content.isDisplayed());
        AssertJUnit.assertTrue("Content should not be empty", content.getText().length() > 0);
    }

    @Then("I should be redirected to the practice questions page")
    public void i_should_be_redirected_to_the_practice_questions_page() {
        wait.until(ExpectedConditions.urlContains("array/practice"));
    }

    @Then("I should see a list of array practice questions")
    public void i_should_see_a_list_of_array_practice_questions() {
        List<WebElement> questions = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='list-group']/a"))//inbuilt class method
        );
        AssertJUnit.assertTrue("At least one question should be present", questions.size() > 0);
    }

    @Then("I should be able to select a question to solve")
    public void i_should_be_able_to_select_a_question_to_solve() {
        WebElement firstQuestion = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='list-group']/a[1]"))
        );
        AssertJUnit.assertTrue("First question should be displayed", firstQuestion.isDisplayed());
    }

    @Given("I am on the array practice questions page")
    public void i_am_on_the_array_practice_questions_page() {
        driver.get("https://dsportalapp.herokuapp.com/array/practice");
        wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='list-group']/a"))//inbuilt class method
        );
    }

    @When("I click on the first practice question")
    public void i_click_on_the_first_practice_question() {
        WebElement firstQuestion = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='list-group']/a[1]"))
        );
        firstQuestion.click();
    }

    @Then("I should be redirected to the question page")
    public void i_should_be_redirected_to_the_question_page() {
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='question-text']"))
        );
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.className("CodeMirror"))
        );
    }

    @When("I enter a valid solution in the code editor")
    public void i_enter_a_valid_solution_in_the_code_editor() {
        // Example solution - adjust based on the specific question
        String solution = "def find_max(arr):\n    if not arr:\n        return None\n    return max(arr)\n\nprint(find_max([1, 2, 3, 4, 5]))";
        
        // Set the code in CodeMirror
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
            "arguments[0].CodeMirror.setValue(arguments[1])",
            driver.findElement(By.className("CodeMirror")),
            solution
        );
    }

    @Then("I should see the correct output")
    public void i_should_see_the_correct_output() {
        WebElement output = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        // Check for presence of output, specific value will depend on question
        AssertJUnit.assertFalse("Output should not be empty", output.getText().trim().isEmpty());
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        WebElement successMessage = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]"))
        );
        AssertJUnit.assertTrue("Success message should be displayed", successMessage.isDisplayed());
    }

    @Given("I am on the {string} page")
    public void i_am_on_the_page(String pageName) {
        if (pageUrls.containsKey(pageName)) {
            driver.get(pageUrls.get(pageName));
        } else {
            throw new IllegalArgumentException("Unknown page name: " + pageName);
        }
    }

    @Then("I should be logged out successfully")
    public void i_should_be_logged_out_successfully() {
        // Look for sign-out success message
        WebElement message = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-success']"))
        );
        AssertJUnit.assertTrue("Message should contain logout confirmation", 
                  message.getText().contains("Logged out successfully"));
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        wait.until(ExpectedConditions.urlContains("https://dsportalapp.herokuapp.com/home"));
    }
}