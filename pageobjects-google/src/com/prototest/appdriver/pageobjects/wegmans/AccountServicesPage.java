package com.prototest.appdriver.pageobjects.wegmans;

import com.prototest.appdriver.Element;
import org.openqa.selenium.By;
import org.testng.Reporter;

/**
 * Created by Brian on 5/27/2014.
 */
public class AccountServicesPage {
    Element AccountServicesHeader = new Element("Account Services header button", By.name("Account Services"));
    Element AccountServicesESpot = new Element("Account Services ESpot", By.id("com.wegmans.wegmansapp:id/login_espot"));
    Element CreateAnAccount = new Element("Create an Online Account button", By.name("Create an Online Account"));
    Element ForgotPassword = new Element("Forgot your Password? button", By.name("Forgot your password?"));
    Element ForgotUsername = new Element("Forgot your username/email? button", By.name("Forgot your username/email?"));
    Element HelpText = new Element("Account Help text", By.name("Need account help? Contact Customer Care Service: 1-800-848-1555 Mon-Fri 8am-6pm EST, Saturday & Sunday 8:00am - 5:00pm or email shoppersclub@wegmans.com"));
    Element UseAppAsGuest = new Element("Use App as Guest button", By.name("use app as guest"));


    public AccountServicesPage VerifyAccountServicesPageElements()
    {
        Reporter.log("Verifying Account Services page elements.");
        AccountServicesESpot.verify().visible();
        AccountServicesHeader.verify().visible();
        CreateAnAccount.verify().visible();
        ForgotPassword.verify().visible();
        ForgotUsername.verify().visible();
        //HelpText.verify().visible();
        UseAppAsGuest.verify().visible();
        return this;
    }

    public SelectStorePage ClickGuestButtonToLoginAsGuest()
    {
        Reporter.log("Using app as a Guest...");
        UseAppAsGuest.click();
        return new SelectStorePage();
    }
}
