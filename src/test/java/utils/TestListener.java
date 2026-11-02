package utils;

import base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (BaseTest.current != null) {
            ScreenshotUtil.capture(BaseTest.current, result.getName());
        }
    }
}