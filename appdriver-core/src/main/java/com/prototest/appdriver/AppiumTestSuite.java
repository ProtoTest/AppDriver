//package com.prototest.appdriver;
//
//import com.google.inject.Inject;
//import org.testng.annotations.*;
//import org.openqa.selenium.WebDriver;
//
//public class AppiumTestSuite extends TestSuite {
//    @Inject
//    Logger logger;
//    private WebDriver driver;
//
//    @AfterMethod
//    public void tearDown() throws Exception {
//        logger.info("Running tearDown() after test");
//        quitBrowser();
//    }
//
//    @BeforeMethod
//    public void setUp() throws Exception {
//        logger.info("Running setUp before test");
//        launchApp();
//    }
//
////    public static WebDriver getDriver() {
////        return driver;
////    }
////
////    public void launchApp() {
////        driver = new BrowserManager().launchAppDriver();
////    }
//
//    public void quitBrowser() {
//        driver.quit();
//    }
//}
