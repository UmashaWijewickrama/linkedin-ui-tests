package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupCheckTest extends BaseTest {
    @Test
    public void browserOpens() {
        driver.get("https://www.linkedin.com/login");
        Assert.assertTrue(driver.getTitle().contains("LinkedIn"));
    }
}