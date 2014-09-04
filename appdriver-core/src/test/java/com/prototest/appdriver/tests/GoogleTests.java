package com.prototest.appdriver.tests;

import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.GolemScreenRecorder;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.WebDriver;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by Brian on 5/20/2014.
 */
public class GoogleTests extends BrowserTestSuite {
    @Test
    public void TestGoogle() {

        new GoogleHomePage();
        buildPage(GoogleHomePage.class).
                searchFor("ProtoTest").
                verifyResult("Home « ProtoTest").
                goToResult("Home « ProtoTest");

    }
    @Test
    public void TestGoogleRhree() {

        new GoogleHomePage();
        buildPage(GoogleHomePage.class).
                searchFor("ProtoTest").
                verifyResult("Home « ProtoTest").
                goToResult("Home « ProtoTest");

    }
    @Test
    public void TestGoogleTwo() {

        new GoogleHomePage();
        buildPage(GoogleHomePage.class).
                searchFor("ProtoTest").
                verifyResult("Home « ProtoTest").
                goToResult("Home « ProtoTest");

    }
}
