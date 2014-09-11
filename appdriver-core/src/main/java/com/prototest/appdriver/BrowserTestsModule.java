package com.prototest.appdriver;

import com.google.inject.AbstractModule;

/**
 */
public class BrowserTestsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.Settings.RuntimeSettings.class).toInstance(new Config.Settings.RuntimeSettings(new Config()));
        bind(Logger.class);
        bind(WebDriverFactory.class);
        bind(PageObjectFactory.class);
        bind(Verifications.class);
        requestStaticInjection(Verifications.class, WebElement.class,AutoInjector.class);

    }
}
