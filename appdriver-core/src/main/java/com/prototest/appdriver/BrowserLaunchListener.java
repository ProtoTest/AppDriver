package com.prototest.appdriver;

import org.testng.*;

/**
 * Created by Brian on 7/18/2014.
 */
public class BrowserLaunchListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            //TestSuite.setDriver(new BrowserManager().launchBrowser(BrowserManager.Browser.Chrome));
        }

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            //TestSuite.getDriver().quit();
        }

    }
}
