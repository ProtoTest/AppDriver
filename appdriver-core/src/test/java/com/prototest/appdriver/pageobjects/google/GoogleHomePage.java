package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.BrowserTestSuite;
import com.prototest.appdriver.Element;
import com.prototest.appdriver.SuperPage;
import org.openqa.selenium.By;

public class GoogleHomePage extends SuperPage {

    static Element searchField = new Element("SearchField", By.name("q"));
    static Element googleLogo = new Element("GoogleLogo", By.id("hplogo"));
    static Element searchButton = new Element("SearchButton", By.name("btnK"));
    static Element feelingLuckyButton = new Element("ImFeelingLuckyButton", By.name("btnI"));
    static Element signInButton = new Element("SignInButon", By.className("gbit"));
    static Element gmailbutton = new Element("GmailButton", By.className("gbts"));

    public static GoogleHomePage openGoogle()
    {

        BrowserTestSuite.getDriver().navigate().to("http://www.google.com/");
        return new GoogleHomePage();
    }

    protected void waitForElements(){
        searchField.verify().visible();
        searchButton.verify().visible();
        feelingLuckyButton.verify().visible();
        signInButton.verify().visible();
        gmailbutton.verify().visible();
    }

    public GoogleResultsPage searchFor(String text)
    {
        searchField.setText(text);
        searchField.submit();
        return new GoogleResultsPage();
    }

}
