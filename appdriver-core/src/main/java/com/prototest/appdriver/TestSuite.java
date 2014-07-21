package com.prototest.appdriver;

import org.testng.annotations.Listeners;

import java.io.File;

@Listeners({TimestampedHTMLReporter.class,
        TimestamppedXMLReporter.class,
        org.uncommons.reportng.JUnitXMLReporter.class,
        VerificationsListener.class,TestListener.class,BrowserLaunchListener.class})
public class TestSuite {

    private static final ThreadLocal<TestContainer> tests = new ThreadLocal<TestContainer>();
//    @BeforeMethod
//    protected void testSuiteBeforeMethod(Method method){
//        Logger.info("Starting test " + method.getName());
//        tests.get().verifications.clearVerifications();
//        tests.set(new TestContainer(method.getName()));
//    }
//
//    @AfterMethod
//    protected void testSuiteAfterMethod(Method method){
//        Logger.info("Ending test " + method.getName());
//        tests.remove();
//    }
//
//    @BeforeTest
//    protected void testSuiteBeforeTest(){
//        createReportDirectory();
//    }
    public static void createTest(String name){
        tests.set(new TestContainer(name));
    }

    public static void deleteTest(){
        tests.remove();
    }

    public static TestContainer getTest(){
        return tests.get();
    }

    protected void createReportDirectory() {
        String testDir = "test-output";
        File report = new File(testDir);
        report.mkdir();
    }

    public static WebDriver getDriver(){
        return tests.get().driver;
    }

    public static WebDriver setDriver(WebDriver driver){
        return tests.get().driver = driver;
    }
}
