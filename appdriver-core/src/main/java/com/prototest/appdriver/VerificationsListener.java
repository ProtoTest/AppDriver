package com.prototest.appdriver;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

/**
 */
public class VerificationsListener implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);

        Verifications.assertVerifications();

    }
}
