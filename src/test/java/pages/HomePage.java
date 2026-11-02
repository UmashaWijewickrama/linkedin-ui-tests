package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchBox = By.cssSelector("input[placeholder='Search']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    public SearchResultsPage search(String term) {
        wait.until(d -> {
            WebElement box = d.findElement(searchBox);
            if (!box.isDisplayed()) {
                return false;
            }
            box.click();
            box.sendKeys(Keys.chord(Keys.CONTROL, "a"), term, Keys.ENTER);
            return true;
        });
        return new SearchResultsPage(driver);
    }
}