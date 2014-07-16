package com.prototest.appdriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter{

    @Override
    public void onTestFailure(ITestResult tr) {
        Logger.error("Test Failed, taking a screenshot");
        Logger.screenshot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        Logger.info("Test was skipped ; " + tr.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.info("Test passed ; " + tr.getTestName());
    }


}