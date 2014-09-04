package com.prototest.appdriver;
import com.google.inject.Inject;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {
    AutoInjector injector = new AutoInjector();
    @Override
    public void onTestFailure(ITestResult tr) {
        injector.logger.error("Test Failed, taking a screenshot");
        injector.logger.screenshot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        injector.logger.info("Test was skipped ; " + tr.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        injector.logger.info("Test passed ; " + tr.getTestName());
    }


}