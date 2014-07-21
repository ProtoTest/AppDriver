package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.Elements.UIElement;
import com.prototest.appdriver.TestSuite;
import org.openqa.selenium.By;

public class GoogleHomePage {

    UIElement searchField = new UIElement("SearchField", By.name("q"));
    //Element googleLogo = new Element("GoogleLogo", By.id("hplogo"));
    UIElement searchButton = new UIElement("SearchButton", By.name("btnK"));
    UIElement feelingLuckyButton = new UIElement("ImFeelingLuckyButton", By.name("btnI"));
    UIElement signInButton = new UIElement("SignInButon", By.className("gbit"));
    UIElement gmailbutton = new UIElement("GmailButton", By.className("gbts"));

    public static GoogleHomePage OpenGoogle()
    {

        TestSuite.getDriver().navigate().to("http://www.google.com/");
        return new GoogleHomePage();
    }

    public GoogleResultsPage SearchFor(String text)
    {
        searchField.setText(text);
        searchField.submit();
        return new GoogleResultsPage();
    }

}
