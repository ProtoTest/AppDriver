package com.prototest.appdriver.pageobjects;

import com.prototest.appdriver.Element;
import org.openqa.selenium.By;
import org.testng.Reporter;

/**
 * Created by Brian on 5/23/2014.
 */
public class LoginPage{
    Element EmailAddress = new Element("Email address field", By.name("Email Address"));
    Element Password = new Element("Password field", By.id("com.wegmans.wegmansapp:id/password"));
    Element AccountServices = new Element("Account Services Button", By.name("Account Services"));
    Element SignIn = new Element("Sign In", By.name("Sign In"));

    public void loginAsUser(String username, String password)
    {
        EmailAddress.sendKeys(username);
        Password.sendKeys(password);
        SignIn.click();
    }

    public void loginAsGuest()
    {
        AccountServices.click();
    }
}
