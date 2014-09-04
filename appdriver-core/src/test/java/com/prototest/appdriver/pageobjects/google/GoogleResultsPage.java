package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.Element;
import com.prototest.appdriver.Logger;
import com.prototest.appdriver.SuperPage;
import org.openqa.selenium.By;

public class GoogleResultsPage extends SuperPage {
    Element googleLogo = new Element("GoogleLogo", By.className("gb_Fa"));
    Element searchField = new Element("SearchField", By.name("q"));
    Element searchButton = new Element("SearchButton", By.id("gbqfb"));
    Element signInButton = new Element("SignInButton", By.linkText("Sign in"));
    Element settingsButton = new Element("SettingsButton", By.id("abar_button_opt"));
    Element servicesButton = new Element("ServicesButton", By.id("gbwa"));
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
        return this;
    }

    public GoogleResultsPage verifyResultNotVisible(String text)
    {
        searchResult(text).verify().not().present();
        searchResult(text).verify().not().visible();
        return this;
    }

    public SearchResultPage goToResult(String text)
    {
        searchResult(text).verify(10).visible();
        searchResult(text).click();
        return buildPage(SearchResultPage.class);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void waitForElements() {
        googleLogo.verify().visible();
        searchField.verify().visible();
        signInButton.verify().visible();
        servicesButton.verify().visible();
        settingsButton.verify().visible();
        searchButton.verify().visible();
    }

    public GoogleHomePage goBack(){
        getDriver().navigate().back();
        return buildPage(GoogleHomePage.class);
    }
}