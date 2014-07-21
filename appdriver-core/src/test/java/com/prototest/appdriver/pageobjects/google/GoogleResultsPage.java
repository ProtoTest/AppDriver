package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.Elements.UIElement;
import org.openqa.selenium.By;

public class GoogleResultsPage {
    //Element googleLogo = new Element("GoogleLogo", By.className("qbqlca"));
    UIElement searchField = new UIElement("SearchField", By.name("q"));
    UIElement searchButton = new UIElement("SearchButton", By.name("btnK"));
    UIElement signInButton = new UIElement("SignInButton", By.className("gbit"));
    UIElement gmailbutton = new UIElement("GmailButton", By.partialLinkText("Gmail"));
    private UIElement searchResult;

    public UIElement searchResult(String text)
    {
        searchResult = new UIElement("SearchResultLink", By.partialLinkText(text));
        return searchResult;
    }

    private String searchString;

    public GoogleResultsPage searchFor(String text)
    {
        searchField.sendKeys(text);
        searchField.submit();
        return new GoogleResultsPage();
    }

    public GoogleResultsPage verifyResult(String text)
    {
        searchResult(text).verify().present();
        searchResult(text).verify().visible();
        return new GoogleResultsPage();
    }

    public GoogleResultsPage verifyResultNotVisible(String text)
    {
        searchResult(text).verify(1).not().present();
        searchResult(text).verify(1).not().visible();
        return new GoogleResultsPage();
    }

    public SearchResultPage goToResult(String text)
    {
        searchResult(text).click();
        return new SearchResultPage();
    }
}