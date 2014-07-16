package com.prototest.appdriver.tests;

import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.testng.annotations.Test;

/**
 * Created by Brian on 5/20/2014.
 */
public class GoogleTests extends BrowserTestSuite {
    @Test
    public void TestGoogle() {

        GoogleHomePage.
                openGoogle().
                searchFor("ProtoTest").
                verifyResult("Home « ProtoTest").
                verifyResultNotVisible("asdfasdf").
                goToResult("Home « ProtoTest");
    }

}
