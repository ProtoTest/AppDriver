package com.prototest.appdriver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserManager {

    public enum Browser {
        Firefox, Chrome, IE, Safari, Android, Iphone;

        public static Browser fromString(String name) {
            for (Browser browser : values()) {
                if (browser.name().compareToIgnoreCase(name) == 0) {
                    return browser;
                }
            }
            return null;
        }
    }

    @Inject
    private WebDriverFactory driverFactory;
    @Inject
    Config.Settings.RuntimeSettings config;
    @Inject
    Logger logger;
    private org.openqa.selenium.WebDriver driver;

    public WebDriver launchBrowser(Browser browser) {
        driver = driverFactory.build(browser);

        logger.debug("launchBrowser(): Using browser = " + browser.toString());
        driver.manage().deleteAllCookies();
        return new WebDriver(driver);
    }

    public DesiredCapabilities getCapabilitiesForBrowser(Browser browser) {
        switch (browser) {
            case IE:
                return DesiredCapabilities.internetExplorer();
            case Chrome:
                return DesiredCapabilities.chrome();
            case Safari:
                return DesiredCapabilities.safari();
            case Android:
                return DesiredCapabilities.android();
            case Firefox:
            default:
                return DesiredCapabilities.firefox();
        }
    }

//    public org.openqa.selenium.WebDriver launchRemoteBrowser(Browser browser, String host)
//    {
//        DesiredCapabilities desiredCapabilities = getCapabilitiesForBrowser(browser);
//        try {
//            URL remoteAddress = new URL("http://"+ host +":4444/wd/hub");
//            //return new EventedWebDriver(new RemoteWebDriver(remoteAddress, desiredCapabilities)).driver;
//        } catch (MalformedURLException e) {
//            Logger.error(e.getMessage());
//        }
//        return null;
//    }
//
//    public org.openqa.selenium.WebDriver launchAppDriver()
//    {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//        capabilities.setCapability("deviceName", Config.Settings.AppiumSettings.deviceName);
//        capabilities.setCapability("platformVersion",Config.Settings.AppiumSettings.platformVersion);
//        capabilities.setCapability("platformName",Config.Settings.AppiumSettings.platformName);
//        capabilities.setCapability("app", Config.Settings.AppiumSettings.appPath);
//        capabilities.setCapability("appPackage", Config.Settings.AppiumSettings.appPackage);
//        capabilities.setCapability("appActivity", Config.Settings.AppiumSettings.appActivity);
//        capabilities.setCapability("resetApp",Config.Settings.AppiumSettings.resetApp);
//        URL url = null;
//        try {
//            url = new URL(String.format("http://%s:4723/wd/hub", Config.Settings.RuntimeSettings.hostIP));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Config hostIP not correct format.  Please check your config.properties file");
//        }
//        return new RemoteWebDriver(url,capabilities);
//    }


}
