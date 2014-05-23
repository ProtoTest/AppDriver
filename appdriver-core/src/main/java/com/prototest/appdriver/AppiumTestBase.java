package com.prototest.appdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

public class AppiumTestBase {
    private static WebDriver driver;
    private static Logger Log = LogManager.getLogger(BrowserTestBase.class.getName());

    @AfterTest
    public void tearDown() throws Exception {
        Log.info("Running tearDown() after test");
        quitBrowser();
    }

    @BeforeTest
    public void setUp() throws Exception {
        Log.info("Running setUp before test");
        launchApp();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void launchApp() {
        driver = new WebDriverLauncher().launchAppDriver();
    }

    public void quitBrowser() {
        driver.quit();
    }
}