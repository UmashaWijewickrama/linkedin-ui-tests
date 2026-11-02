package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;
import utils.Config;
import java.time.Duration;

public class SearchResultTest extends BaseTest {

    @Test(description = "TC04 - clicking the first result opens the matching page")
    public void openFirstResult() {
        LoginPage login = new LoginPage(driver).open();
        login.login(Config.username(), Config.password());
        Assert.assertTrue(login.isLoggedIn(), "Login failed");

        // A public figure keeps the first result predictable and avoids opening private profiles
        SearchResultsPage results = new HomePage(driver).search("Satya Nadella");
        Assert.assertTrue(results.isDisplayed());

        String href = results.clickFirstResult();
        String slug = SearchResultsPage.slugOf(href);

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlContains(slug));

        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains(slug), "Opened URL does not match the clicked result: " + url);
        Assert.assertTrue(url.contains("/in/") || url.contains("/company/") || url.contains("/school/"),
                "Unexpected page type: " + url);
    }
}