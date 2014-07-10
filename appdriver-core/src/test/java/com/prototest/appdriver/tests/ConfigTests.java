package com.prototest.appdriver.tests;

import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.TestSuite;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Brian on 5/20/2014.
 */
public class ConfigTests extends BrowserTestSuite {
    @Test
    public void TestConfigValue() {
        Logger.info("Test");
        driver.navigate().to("http://www.google.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElements(By.xpath("//*"));
    }


}
