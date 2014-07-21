package com.prototest.appdriver;

import com.google.inject.Inject;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Guice(modules = {BrowserTestsModule.class})
public class BrowserTestSuite extends TestSuite {
    public Config.Settings.RuntimeSettings getConfig() {
        return config;
    }

    @Inject
    Config.Settings.RuntimeSettings config;

    @Inject
    Logger logger;

    @Inject
    PageObjectFactory pageObjectFactory;

    @Inject
    WebDriverFactory driverFactory;

    @BeforeTest
    @Parameters({"browser", "hostName", "hostPort"})
    void testSuiteBeforeTest(@Optional String browser, @Optional String hostName,
                             @Optional Integer hostPort) {
        super.testSuiteBeforeTest();

        createReportDirectory();
    }

    @AfterMethod
    public void browserTestAfterMethod(Method method) throws Exception {
        super.testSuiteAfterMethod(method);
        logger.info("Running tearDown() after test");
        quitBrowser();
    }

    @BeforeMethod
    @Parameters({"browser", "hostName", "hostPort"})
    public void browserTestBeforeMethod(Method method, @Optional String browser, @Optional String hostName,
                                        @Optional Integer hostPort) throws Exception {
        super.testSuiteBeforeMethod(method);
        logger.info("Running setUp before test");
        driverFactory.build(config.browser);
    }

    public WebDriver getDriver() {
        return driverFactory.get();
    }

    public void quitBrowser() {
        getDriver().quit();
        deleteDriver();
    }

    private void deleteDriver() {
        driverFactory.deleteDriver();
    }


    protected <P extends SuperPage> P buildPage(Class<P> pageObjectClazz) {
        return pageObjectFactory.of(pageObjectClazz);
    }
}
