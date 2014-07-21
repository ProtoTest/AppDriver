package com.prototest.appdriver.tests;

import com.prototest.appdriver.pageobjects.google.GoogleHomePage_Factory;
import com.prototest.appdriver.TestSuite;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.testng.annotations.Test;

/**
 * Created by Brian on 5/20/2014.
 */
public class GoogleTests extends TestSuite {
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
