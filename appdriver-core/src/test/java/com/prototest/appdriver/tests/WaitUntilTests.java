package com.prototest.appdriver.tests;

import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Element;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import com.prototest.appdriver.tests.SuperClass;
import junit.framework.AssertionFailedError;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaitUntilTests extends BrowserTestSuite {

    @Test
    public void Test_WaitUntil_GoodLink() {
        GoogleHomePage.
                openGoogle();
        Element searchField = new Element(By.name("q"));
        searchField.waitUntil().present().visible();
    }

    @Test(expectedExceptions= AssertionError.class)
    public void Test_WaitUntil_BadLink() {
        GoogleHomePage.
                openGoogle();
        Element searchField = new Element(By.name("qzz"));
        searchField.waitUntil().present().visible();
    }
    @Test(expectedExceptions= AssertionError.class)
    public void Test_WaitUntilNot_GoodLink() {
        GoogleHomePage.
                openGoogle();
        Element searchField = new Element(By.name("q"));
        searchField.waitUntil().not().present().visible();
    }
    @Test
    public void Test_WaitUntilNot_BadLink() {
        GoogleHomePage.
                openGoogle();
        Element searchField = new Element(By.name("qzz"));
        searchField.waitUntil().not().present().visible();
    }

}