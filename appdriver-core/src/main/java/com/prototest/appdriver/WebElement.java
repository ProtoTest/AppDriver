package com.prototest.appdriver;

interface WebElement extends org.openqa.selenium.WebElement {
    default public void highlight(){
        BrowserTestSuite.driver.highlight(this);
    }

    default public void setText(String value) {
        clear();
        sendKeys(value);
    }


}
