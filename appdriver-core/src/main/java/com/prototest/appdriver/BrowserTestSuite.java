package com.prototest.appdriver;

import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BrowserTestSuite extends TestSuite {
    @DataProvider(name = "browsers", parallel = true)
    public static Object[][] getData() {
        return new Object[][] {
                {"Object 1", 1, true},
                {"Object 2", 2, false}
        };
    }


    @BeforeTest (dataProvid)
    public void getBrowsers(){

    }

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
        driver = new WebDriverLauncher().launchBrowser(WebDriverLauncher.Browser.Firefox);
    }

    public void quitBrowser() {
        driver.quit();
    }
}
