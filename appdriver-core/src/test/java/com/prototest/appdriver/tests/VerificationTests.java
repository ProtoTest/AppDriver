package com.prototest.appdriver.tests;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Element;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import junit.framework.AssertionFailedError;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Brian on 7/16/2014.
 */
public class VerificationTests extends BrowserTestSuite {
    @Test
    public void TestVerification_GoodLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("q"));
        searchField.verify().present().visible();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void TestVerification_BadLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("qzz"));
        searchField.verify().present().visible();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void TestNot_GoodLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("q"));
        searchField.verify().not().present().visible();
    }

    @Test
    public void TestNot_BadLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("qzz"));
        searchField.verify().not().present().visible();
    }


    @Test
    public void TestContains_GoodText() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().containsValue("selenium");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void TestContains_BadText() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().containsValue("seleniumz");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void TestContains_BadLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("qz"));
        searchField.verify().containsText("selenium");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void TestNotContains_GoodLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().not().containsValue("selenium");
    }

    @Test
    public void TestNotContains_BadLink() {
        buildPage(GoogleHomePage.class);
        Element searchField = new Element(By.name("qz"));
        searchField.verify().not().containsText("selenium");
    }


}
