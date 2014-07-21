package com.prototest.appdriver.tests;

import com.prototest.appdriver.Elements.UIElement;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Brian on 7/16/2014.
 */
public class VerificationTests {
    @Test
    public void TestVerification_GoodLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.verify().present().visible();
    }

    @Test(expectedExceptions= AssertionError.class)
    public void TestVerification_BadLink() {
        GoogleHomePage.
        openGoogle();
        UIElement searchField = new UIElement(By.name("qzz"));
        searchField.verify().present().visible();
    }
    @Test(expectedExceptions= AssertionError.class)
    public void TestNot_GoodLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.verify().not().present().visible();
    }
    @Test
    public void TestNot_BadLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("qzz"));
        searchField.verify().not().present().visible();
    }


    @Test
    public void TestContains_GoodText() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().containsValue("selenium");
    }

    @Test(expectedExceptions= AssertionError.class)
    public void TestContains_BadText() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().containsValue("seleniumz");
    }

    @Test(expectedExceptions= AssertionError.class)
    public void TestContains_BadLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("qz"));
        searchField.verify().containsText("selenium");
    }

    @Test(expectedExceptions= AssertionError.class)
    public void TestNotContains_GoodLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.sendKeys("selenium");
        searchField.verify().not().containsValue("selenium");
    }
    @Test
    public void TestNotContains_BadLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("qz"));
        searchField.verify().not().containsText("selenium");
    }


}
