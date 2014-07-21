package com.prototest.appdriver;
import com.google.inject.Inject;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {
    @Inject
    Logger logger;

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.error("Test Failed, taking a screenshot");
        logger.screenshot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.info("Test was skipped ; " + tr.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info("Test passed ; " + tr.getTestName());
    }


}