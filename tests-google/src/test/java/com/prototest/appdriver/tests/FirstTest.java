package com.prototest.appdriver.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Brian on 5/26/2014.
 */
public class FirstTest {

    @Test
    public void AppTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("appium-version","1.0");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("app", "C:\\Users\\Brian\\Documents\\GitHub\\AppDriver\\Wegmans.apk");
        capabilities.setCapability("appPackage", "com.wegmans.wegmansapp");
        capabilities.setCapability("appActivity", "com.wegmans.android.wegmans.common.activities.StartupActivity");
        capabilities.setCapability("resetApp",true);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.findElement(By.name("Account Services")).click();
        driver.quit();

    }

    @Test
    public void BrowserTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", "chrome");
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test
    public void LongTest() throws MalformedURLException {

    }
}
