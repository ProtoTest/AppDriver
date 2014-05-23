package com.prototest.appdriver.tests;

import com.prototest.appdriver.BrowserTestBase;
import com.prototest.appdriver.Config;
import com.prototest.appdriver.WebDriverLauncher;
import com.prototest.appdriver.pageobjects.google.GoogleHomePage;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class MyFirstTestNGBrowserTest extends BrowserTestBase {
    @Test
    public static void Test() {
        System.out.println("Test Timeout min " + Config.Settings.RuntimeSettings.testTimeoutMin);
        System.out.println("Launch Browser? " + Config.Settings.RuntimeSettings.launchBrowser);
        System.out.println("Host IP? " + Config.Settings.RuntimeSettings.hostIP);



        for(WebDriverLauncher.Browser browser : Config.Settings.RuntimeSettings.browsers) {
            System.out.println("Browser: " + browser.toString());
        }

        boolean launchBrowser = Config.Settings.RuntimeSettings.launchBrowser;
        System.out.println("Shall I launch the browser?" + launchBrowser);


        GoogleHomePage.
                OpenGoogle().
                SearchFor("ProtoTest").
                VerifyResult("ProtoTest « Beyond Bugs").
                VerifyResultNotVisible("ProtoTest « Beyond Bugs").
                //VerifyResult("Psdfsdf").
                VerifyResultNotVisible("SDLFKJSDF").
                GoToResult("ProtoTest « Beyond Bugs");
    }

    @Test
    public void testLogin() {
        System.out.println("Running Login google");
        Reporter.log("Here is a Test Log that is supposed to be in the Report!");
    }
}
