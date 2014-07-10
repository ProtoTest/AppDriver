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
        VerificationsListener.class})
public class TestSuite {
    public static Dictionary<String, TestContainer> tests;
    static{
        tests = new Dictionary<String, TestContainer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<String> keys() {
                return null;
            }

            @Override
            public Enumeration<TestContainer> elements() {
                return null;
            }

            @Override
            public TestContainer get(Object key) {
                return null;
            }

            @Override
            public TestContainer put(String key, TestContainer value) {
                return null;
            }

            @Override
            public TestContainer remove(Object key) {
                return null;
            }
        };
    }

    public TestContainer getTest(String name){
        return tests.get(name);
    }

    public void addTest(String name, TestContainer container){
        tests.put(name,container);
    }

    public void deleteTest(String name){
        tests.remove(name);
    }


    @BeforeMethod
    void testSuiteBeforeMethod(Method method){
        Logger.info("Starting test " + method.getName());
        Verifications.clearVerifications();
        addTest(method.getName(),new TestContainer(method.getName()));
    }

    @AfterMethod
    void testSuiteAfterMethod(Method method){
        Logger.info("Ending test " + method.getName());
        deleteTest(method.getName());
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
