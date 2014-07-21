package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.TestSuite;
import com.prototest.appdriver.Elements.Element;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage_Factory {
    @FindBy(name = "q") Element searchField;
    @FindBy(id = "hplogo") Element googleLogo;
    @FindBy(name="btnK")
    Element searchButton;
    @FindBy(name="btnI")
    Element feelingLuckyButton;// = new Element("ImFeelingLuckyButton", By.name("btnI"));
//    static Element signInButton = new Element("SignInButon", By.className("gbit"));
//    static Element gmailbutton = new Element("GmailButton", By.className("gbts"));

    public static GoogleHomePage_Factory openGoogle()
    {

        TestSuite.getDriver().navigate().to("http://www.google.com/");
        return new GoogleHomePage_Factory();
    }

    protected void waitForElements(){
        searchField.verify(10).visible();
        googleLogo.verify(10).visible();
        searchButton.verify(10).visible();
    }

    public GoogleResultsPage searchFor(String text)
    {
        searchField.sendKeys(text);
        searchField.submit();
        return new GoogleResultsPage();
    }

}
