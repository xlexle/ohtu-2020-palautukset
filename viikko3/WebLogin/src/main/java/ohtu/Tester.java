package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {
    private static final String testPassword = "salasana";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        sleep(2);

        String username = registerSuccessScenario(driver);
        sleep(2);
        loginSuccessScenario(driver, username);
        sleep(2);
        logoutSuccessScenario(driver, username);
        sleep(2);
        loginFailWrongPasswordScenario(driver, username);

        driver.quit();
    }

    private static String registerSuccessScenario(WebDriver driver) {
        driver.get("http://localhost:4567");
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);

        String username = registerRandom(driver);
        sleep(2);

        return username;
    }

    private static void loginSuccessScenario(WebDriver driver, String username) {
        driver.get("http://localhost:4567");
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        loginUser(driver, username);
        sleep(2);
    }

    private static void logoutSuccessScenario(WebDriver driver, String username) {
        loginSuccessScenario(driver, username);

        WebElement element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(2);
    }

    private static void loginFailWrongPasswordScenario(WebDriver driver, String username) {
        driver.get("http://localhost:4567");
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        loginUser(driver, username, "incorrectpass");
        sleep(2);
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static String registerRandom(WebDriver driver){
        Random r = new Random();
        String username = "user" + r.nextInt(100000);
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(testPassword);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(testPassword);
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();
        return username;
    }

    private static void loginUser(WebDriver driver, String username) {
        loginUser(driver, username, testPassword);
    }

    private static void loginUser(WebDriver driver, String username, String password) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
    }

}
