package com.prototest.appdriver.pageobjects.google;

import com.prototest.appdriver.SuperPage;

/**
 * Created by Brian on 7/15/2014.
 */
public class SearchResultPage extends SuperPage {
    @Override
    protected void init() {

    }

    public GoogleResultsPage goBack(){
        getDriver().navigate().back();
        return buildPage(GoogleResultsPage.class);
    }

    @Override
    protected void waitForElements() {

    }
}
