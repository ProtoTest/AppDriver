package com.prototest.appdriver.tests;

import com.google.inject.Inject;
import com.prototest.appdriver.BrowserManager;
import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Config;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.TestContainer;
import com.prototest.appdriver.Verifications;
import com.prototest.appdriver.WebDriverFactory;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by Brian on 5/20/2014.
 */
public class ParallelTests extends BrowserTestSuite {
    @Inject
    Logger logger;
    @Inject
    WebDriverFactory driverFactory;
    @Inject
    Config.Settings.RuntimeSettings config;

    // For choosing a random browser.
    static Random random = new Random();
    // Duplicating code from super classes...
//    private static final ThreadLocal<TestContainer> tests = new ThreadLocal<TestContainer>();

    @Override
    @BeforeMethod
    @Parameters({"browser", "hostName", "hostPort"})
    public void browserTestBeforeMethod(Method method, @Optional String browser, @Optional String hostName,
                                        @Optional Integer hostPort) throws Exception {
        logger.info("Starting test " + method.getName());
        Verifications.clearVerifications();
        logger.info("Running setUp before test");
        boolean setChrome = random.nextBoolean();
        logger.info("!!!!!!!!! setChrome = " + setChrome + " thread id is " + Thread.currentThread().getId());
        driverFactory.build(setChrome ? BrowserManager.Browser.Chrome : config.browser);
        logger.info("!!!!!!!!! setChrome = " + setChrome + " current browsa is " + driverFactory.get().driver.getClass().getSimpleName() + " thread id is " + Thread.currentThread().getId());

    }
    @Test(invocationCount = 10, threadPoolSize = 5)
    public void TestParallel() {

        new GoogleHomePage();
        buildPage(GoogleHomePage.class).
                searchFor("ESPN").
                verifyResult("ESPN: The Worldwide Leader In Sports").
                verifyResult("ESPN Fantasy Football").
                verifyResult("ESPN - YouTube").
                goToResult("ESPN: The Worldwide Leader In Sports");
    }

}
