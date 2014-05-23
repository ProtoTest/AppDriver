package com.prototest.appdriver.pageobjects;

import com.prototest.appdriver.Element;
import org.openqa.selenium.By;
import org.testng.Reporter;

/**
 * Created by Brian on 5/23/2014.
 */
public class LoginPage{
    Element LoginESpot = new Element("Login ESpot", By.id("com.wegmans.wegmansapp:id/login_espot"));
    Element LoginHelpText = new Element("Login help text", By.name("Sign in with your Wegmans Online Account"));
    Element EmailAddress = new Element("Email address field", By.name("Email Address"));
    Element Password = new Element("Password field", By.xpath("//EditText[2]"));
    Element AccountServices = new Element("Account Services Button", By.name("Account Services"));
    Element SignIn = new Element("Sign In", By.name("Sign In"));

    public void WaitForElements()
    {
        Reporter.log("Entering Login page.");
        SignIn.Verify.IsPresent();
    }

    public LoginPage VerifyLoginPageElements()
    {
        Reporter.log("Verifying Login page elements.");
        LoginESpot.Verify.IsPresent();
        LoginHelpText.Verify.IsVisible();
        EmailAddress.Verify.IsVisible();
        AccountServices.Verify.IsVisible();
        SignIn.Verify.IsVisible();
        return this;
    }

    public void loginAsUser(String username, String password)
    {
        Reporter.log("Logging in with User data..");
        EmailAddress.sendKeys(username);
        Password.sendKeys(password);
        SignIn.click();
    }

    public void ClickAccountServicesToLoginAsGuest()
    {
        Reporter.log("Logging in as a Guest..");
        AccountServices.click();
    }
}
