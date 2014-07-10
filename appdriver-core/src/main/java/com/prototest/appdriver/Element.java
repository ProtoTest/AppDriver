
package com.prototest.appdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//Element class can be instantiated any time but only looks for the element on the page when a function is called
public class Element{
        private By by;
        private WebDriver driver;
        private String name;
        private WebElement element;
        public Verification Verify;

        public Element(String name, By by) {
            this.name = name;
            this.by = by;
            this.Verify = new Verification();
            this.driver = BrowserTestSuite.getDriver();
        }



    public Verification Verify(int timeoutSec){
        return new Verification(this,timeoutSec);
    }

    public By getBy(){
        return by;
    }

    public WebElement getElement()
    {
        if(element!=null)return element;
        List<WebElement> elements = driver.findElements(this.by);
        for(org.openqa.selenium.WebElement element : elements){
            if(element.isDisplayed()&&element.isEnabled()){
                this.element = (WebElement) element;
            }
        }
        return this.element;
    }

    public Point getLocation() {
        return getElement().getLocation();
    }

    public Dimension getSize() {
        return getElement().getSize();
    }

    public String getTagName() {
        return getElement().getTagName();
    }

    public String getText() {
        return getElement().getText();
    }

    public String getAttribute(String attribute) {
        try {
            return getElement().getAttribute(attribute);
        }
        catch (Exception e) {
            System.out.println("Failed to get attribute: " + attribute);
            e.printStackTrace();
        }

        return "";
    }

    public boolean isSelected() {
        if (getElement().isSelected()) return true;
        else return false;
    }

    public boolean isEnabled() {
        if (getElement().isEnabled()) return true;
        else return false;
    }

    public String getCssValue(String css) {
        return getElement().getCssValue(css);
    }

    public Element setCheckbox(boolean checked) {
        if(getElement().isSelected() != checked) {
            getElement().click();
        }

        return this;
    }

    // this shit crashes
    public void setText(String text) {
        getElement().clear();
        getElement().sendKeys(text);
    }

    public org.openqa.selenium.WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    public boolean isDisplayed() {
        if ((isPresent())&&(getElement().isDisplayed())) return true;
        else
            return false;
    }

    public boolean isPresent() {
        if(driver.findElements(by).size()>0)
            return true;
        else
            return false;
    }


    public List<org.openqa.selenium.WebElement> findElements(By by) {
        return getElement().findElements(by);
    }

    public void clear() {
        getElement().clear();
    }

    public void click() {
        getElement().click();
    }

    public void submit() {
        getElement().submit();
    }

    public void sendKeys(String text) {
        waitUntilVisible();
        getElement().sendKeys(text);
    }

    public Element waitUntilVisible() {
        WebDriverWait wait = new WebDriverWait((org.openqa.selenium.WebDriver)driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }
}
