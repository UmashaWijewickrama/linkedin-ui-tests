package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // The id is generated dynamically, so use the stable placeholder text
    private final By searchBox = By.cssSelector("input[placeholder='Search']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public SearchResultsPage search(String term) {
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.click();
        box.sendKeys(term, Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}