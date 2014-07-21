package com.prototest.appdriver.pageobjects.wegmans;

import com.prototest.appdriver.Elements.UIElement;
import org.openqa.selenium.By;

/**
 * Created by Brian on 5/23/2014.
 */
public class LoginPage{
    UIElement EmailAddress = new UIElement("Email address field", By.name("Email Address"));
    UIElement Password = new UIElement("Password field", By.id("com.wegmans.wegmansapp:id/password"));
    UIElement AccountServices = new UIElement("Account Services Button", By.name("Account Services"));
    UIElement SignIn = new UIElement("Sign In", By.name("Sign In"));

    public MyListsPage loginAsUser(String username, String password)
    {
        EmailAddress.waitUntilVisible().click();
        EmailAddress.sendKeys(username);
        Password.sendKeys(password);
        SignIn.click();
        return new MyListsPage();
    }

    public AccountServicesPage loginAsGuest()
    {
        AccountServices.click();
        return new AccountServicesPage();
    }
}
