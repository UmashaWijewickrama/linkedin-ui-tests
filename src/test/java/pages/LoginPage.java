package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Ids are generated dynamically, so use stable attributes instead
    private final By emailField = By.cssSelector("input[type='email']");
    private final By passwordField = By.cssSelector("input[type='password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // The page has duplicate fields, so wait for the first one that is displayed
    private WebElement firstVisible(By by) {
        return wait.until(d -> d.findElements(by).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null));
    }

    public LoginPage open() {
        driver.get(Config.BASE_URL + "/login");
        firstVisible(emailField);
        return this;
    }

    public void login(String user, String pass) {
        firstVisible(emailField).sendKeys(user);
        WebElement pw = firstVisible(passwordField);
        pw.sendKeys(pass);
        pw.sendKeys(Keys.ENTER);   // submits the form (same as clicking Sign in)
    }

    // If LinkedIn shows a CAPTCHA or checkpoint, solve it by hand (up to 2 minutes). Never bypass it.
    public void waitForManualCheckpointIfAny() {
        if (driver.getCurrentUrl().contains("checkpoint")) {
            System.out.println(">>> Verification shown. Complete it manually in the browser...");
            new WebDriverWait(driver, Duration.ofMinutes(2))
                    .until(d -> !d.getCurrentUrl().contains("checkpoint"));
        }
    }

    public boolean isLoggedIn() {
        waitForManualCheckpointIfAny();
        return wait.until(ExpectedConditions.urlContains("/feed"));
    }
}