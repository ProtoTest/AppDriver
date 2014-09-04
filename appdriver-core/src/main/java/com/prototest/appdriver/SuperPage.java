package com.prototest.appdriver;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 7/15/2014.
 */
public abstract class SuperPage {

    @Inject
    private WebDriverFactory driverFactory;

    @Inject
    private Config.Settings.RuntimeSettings config;

    @Inject
    private PageObjectFactory pageObjectFactory;


    protected void init(){

    }
    /**
     * Verifies elements on the page are present. The default implementation does nothing.
     */
    abstract protected void waitForElements();

    protected WebDriver getDriver() {
        return driverFactory.get();
    }

    protected Config.Settings.RuntimeSettings getConfig() {
        return config;
    }

    protected <P extends SuperPage> P buildPage(Class<P> pageObjectClazz) {
        return pageObjectFactory.of(pageObjectClazz);
    }
}