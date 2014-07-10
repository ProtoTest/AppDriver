package com.prototest.appdriver;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

public class AppiumTestSuite extends TestSuite {
    private static WebDriver driver;

    @AfterMethod
    public void tearDown() throws Exception {
        Logger.info("Running tearDown() after test");
        quitBrowser();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        Logger.info("Running setUp before test");
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
