package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchResultsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Result links to a person, company or school (class names are dynamic, hrefs are stable)
    private final By resultLinks = By.xpath(
            "//a[contains(@href,'/in/') or contains(@href,'/company/') or contains(@href,'/school/')]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // The results list re-renders while loading, so ignore stale references and retry
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("/search/results/"));
    }

    private WebElement firstResult() {
        return wait.until(d -> d.findElements(resultLinks).stream()
                .filter(WebElement::isDisplayed)
                .filter(e -> !e.getText().isBlank())
                .findFirst()
                .orElse(null));
    }

    public boolean hasVisibleResult() {
        return firstResult().isDisplayed();
    }

    // Clicks the first result and returns the href it pointed to
    public String clickFirstResult() {
        WebElement first = firstResult();
        String href = first.getAttribute("href");
        try {
            first.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
        }
        return href;
    }

    // "https://www.linkedin.com/in/some-name-123/?x=y" -> "some-name-123"
    public static String slugOf(String href) {
        String path = href.split("\\?")[0].replaceAll("/$", "");
        return path.substring(path.lastIndexOf('/') + 1);
    }
}