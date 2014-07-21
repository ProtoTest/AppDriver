package com.prototest.appdriver.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Brian on 7/15/2014.
 */
public class TestFactory {
    @Test
    public void TestIE() throws InterruptedException {
        String currentModulePath = null;
        try {
            currentModulePath = new java.io.File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String iePath = currentModulePath += "\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", iePath);
        DesiredCapabilities ieCapabilities =  DesiredCapabilities.internetExplorer();
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.quit();
    }
}
