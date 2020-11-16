package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RegistrationStepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("register new user is selected")
    public void registerNewUserIsSelected() {
        goToRegisterNewUserPage();
    }

    @When("valid username {string}, valid password {string} and matching password confirmation are entered")
    public void validUsernameValidPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        registerWith(username, password, password);
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("too short username {string} is entered")
    public void tooShortUsernameIsEntered(String username) {
        registerWith(username, "", "");
    }

    @When("valid username {string} and too short password {string} are entered")
    public void validUsernameAndTooShortPasswordAreEntered(String username, String password) {
        registerWith(username, password, password);
    }

    @When("valid username {string}, valid password {string} and non-matching password confirmation {string} are entered")
    public void validUsernameValidPasswordAndNonMatchingPasswordConfirmationAreEntered(String username, String password, String confirmation) {
        registerWith(username, password, confirmation);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String error) {
        pageHasContent(error);
    }

    @Given("user with username {string} and password {string} is successfully created")
    public void userWithUsernameAndPasswordIsSuccessfullyCreated(String username, String password) {
        goToRegisterNewUserPage();
        registerWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        goToRegisterNewUserPage();
        registerWith(username, password, password);
        pageHasSomeError();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void pageHasSomeError() {
        String error = driver.findElement(By.xpath("//div[@id='error']/p/em")).getText();
        assertFalse(error.isEmpty());
    }

    private void goToRegisterNewUserPage() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    private void registerWith(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
