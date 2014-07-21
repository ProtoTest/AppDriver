package com.prototest.appdriver.tests;

import com.prototest.appdriver.Elements.UIElement;
import com.prototest.appdriver.TestSuite;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class WaitUntilTests extends TestSuite {

    @Test
    public void Test_WaitUntil_GoodLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.waitUntil().present().visible();
    }

    @Test(expectedExceptions= AssertionError.class)
    public void Test_WaitUntil_BadLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("qzz"));
        searchField.waitUntil().present().visible();
    }
    @Test(expectedExceptions= AssertionError.class)
    public void Test_WaitUntilNot_GoodLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("q"));
        searchField.waitUntil().not().present().visible();
    }
    @Test
    public void Test_WaitUntilNot_BadLink() {
        GoogleHomePage.
                openGoogle();
        UIElement searchField = new UIElement(By.name("qzz"));
        searchField.waitUntil().not().present().visible();
    }

}