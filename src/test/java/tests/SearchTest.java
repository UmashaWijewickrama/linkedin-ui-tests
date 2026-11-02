package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;
import utils.Config;

public class SearchTest extends BaseTest {

    @Test(description = "TC03 - search shows a results page")
    public void searchShowsResults() {
        LoginPage login = new LoginPage(driver).open();
        login.login(Config.username(), Config.password());
        Assert.assertTrue(login.isLoggedIn(), "Login failed");

        SearchResultsPage results = new HomePage(driver).search("software testing");

        Assert.assertTrue(results.isDisplayed(), "Results page not displayed");
        Assert.assertTrue(driver.getCurrentUrl().contains("/search/results/"));
        Assert.assertTrue(results.hasVisibleResult(), "No visible result element");
    }
}