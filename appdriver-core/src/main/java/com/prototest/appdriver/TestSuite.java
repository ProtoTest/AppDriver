package com.prototest.appdriver;

/**
 * Created by Brian on 7/7/2014.
 */

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.Enumeration;

@Listeners({TimestampedHTMLReporter.class,
        TimestamppedXMLReporter.class,
        org.uncommons.reportng.JUnitXMLReporter.class,
        VerificationsListener.class,ScreenshotListener.class})
public class TestSuite {

    private static final ThreadLocal<TestContainer> tests = new ThreadLocal<TestContainer>();

    @BeforeMethod
    void testSuiteBeforeMethod(Method method){
        Logger.info("Starting test " + method.getName());
        Verifications.clearVerifications();
        tests.set(new TestContainer(method.getName()));
    }

    @AfterMethod
    void testSuiteAfterMethod(Method method){
        Logger.info("Ending test " + method.getName());
        tests.remove();
    }

    @BeforeTest
    void testSuiteBeforeTest(){
        createReportDirectory();
    }

    void createReportDirectory() {
        String testDir = "test-output";
        File report = new File(testDir);
        report.mkdir();
    }
}
