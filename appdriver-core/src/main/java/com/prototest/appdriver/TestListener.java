package com.prototest.appdriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter{

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

    @Override
    public void onStart(ITestContext testContext){
        TestSuite.createTest(testContext.getName());
        new BrowserManager().launchBrowser(BrowserManager.Browser.Chrome);
    }
    @Override
    public void onFinish(ITestContext testContext){
        TestSuite.getDriver().quit();
        TestSuite.deleteTest();
    }

}