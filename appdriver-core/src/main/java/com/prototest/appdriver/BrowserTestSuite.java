package com.prototest.appdriver;

import junit.framework.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrowserTestSuite extends TestSuite {
//    @DataProvider
//    public static Iterator<Object[]> fileDataProvider () {
//        List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
////
////        for(int i=1; i<=10 ;i++){
////
////        }
//        dataToBeReturned.add(new Object[] { "Firefox" } );
//        dataToBeReturned.add(new Object[] { "Chrome" } );
//        dataToBeReturned.add(new Object[] { "IE" } );
//        //return the iterator - testng will initialize the test class and calls the
//        //test method with each of the content of this iterator.
//        return dataToBeReturned.iterator();
//
//    }
    private  String browser = null;
    //Constructor: test data in this case String line can be utilized by all the @Test methods.
//    @Factory(dataProvider="fileDataProvider")
//    public BrowserTestSuite(String browser) {
//        this.browser = browser;
//         System.out.println("In factory " + browser);
//
//    }

    public static WebDriver driver;

    @AfterMethod
    public void browserTestAfterMethod(Method method) throws Exception {
        super.testSuiteAfterMethod(method);
        Logger.info("Running tearDown() after test");
        quitBrowser();
    }

    @BeforeMethod
    public void browserTestBeforeMethod(Method method) throws Exception {
        super.testSuiteBeforeMethod(method);
        Logger.info("Running setUp before test");
        launchBrowser();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void launchBrowser() {
        try{
            driver = new BrowserManager().launchBrowser(BrowserManager.Browser.IE);
        }
        catch(Exception e){
           throw new RuntimeException(e.getMessage());
        }
}
    public void quitBrowser() {
        driver.quit();
    }
}
