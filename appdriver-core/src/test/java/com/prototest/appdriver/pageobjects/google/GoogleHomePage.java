package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.Element;
import com.prototest.appdriver.SuperPage;
import org.openqa.selenium.By;

public class GoogleHomePage extends SuperPage {

    Element searchField = new Element("SearchField", By.id("gbqfq"));
    Element googleLogo = new Element("GoogleLogo", By.id("hplogo"));
    Element searchButton = new Element("SearchButton", By.name("btnK"));
    Element feelingLuckyButton = new Element("ImFeelingLuckyButton", By.name("btnI"));
    Element signInButton = new Element("SignInButon", By.id("gb_70"));
    Element gmailbutton = new Element("GmailButton", By.linkText("Gmail"));

    @Override
    public void init() {
        getDriver().navigate().to("http://www.google.com/");
    }

    @Override
    protected void waitForElements() {
        googleLogo.verify().visible();
        searchField.verify().visible();
        searchButton.verify().visible();
        feelingLuckyButton.verify().visible();
        signInButton.verify().visible();
        gmailbutton.verify().visible();
    }

    public GoogleResultsPage searchFor(String text) {
        searchField.setText(text);
        searchField.submit();
        return buildPage(GoogleResultsPage.class);
    }

}
