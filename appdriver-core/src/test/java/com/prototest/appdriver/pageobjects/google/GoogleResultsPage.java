package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.Element;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.SuperPage;
import org.openqa.selenium.By;

public class GoogleResultsPage extends SuperPage {
    //Element googleLogo = new Element("GoogleLogo", By.className("qbqlca"));
    Element searchField = new Element("SearchField", By.name("q"));
    Element searchButton = new Element("SearchButton", By.name("btnK"));
    Element signInButton = new Element("SignInButton", By.className("gbit"));
    Element gmailbutton = new Element("GmailButton", By.partialLinkText("Gmail"));
    private Element searchResult;

    public Element searchResult(String text)
    {
        searchResult = new Element("SearchResultLink", By.partialLinkText(text));
        return searchResult;
    }

    private String searchString;

    public GoogleResultsPage searchFor(String text)
    {
        searchField.sendKeys(text);
        searchField.submit();
        return buildPage(GoogleResultsPage.class);
    }

    public GoogleResultsPage verifyResult(String text)
    {
        searchResult(text).verify().present();
        searchResult(text).verify().visible();
        return buildPage(GoogleResultsPage.class);
    }

    public GoogleResultsPage verifyResultNotVisible(String text)
    {
        searchResult(text).verify().not().present();
        searchResult(text).verify().not().visible();
        return buildPage(GoogleResultsPage.class);
    }

    public SearchResultPage goToResult(String text)
    {
        searchResult(text).click();
        return buildPage(SearchResultPage.class);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void waitForElements() {

    }
}