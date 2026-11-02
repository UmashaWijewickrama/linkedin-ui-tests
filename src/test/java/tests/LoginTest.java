package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Config;

public class LoginTest extends BaseTest {

    @Test(description = "TC01 - valid login")
    public void validLogin() {
        LoginPage page = new LoginPage(driver).open();
        page.login(Config.username(), Config.password());
        Assert.assertTrue(page.isLoggedIn(), "Feed page was not reached");
        Assert.assertTrue(driver.getCurrentUrl().contains("/feed"));
    }
}