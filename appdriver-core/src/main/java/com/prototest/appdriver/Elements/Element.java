package com.prototest.appdriver.Elements;

import com.prototest.appdriver.Config;
import com.prototest.appdriver.Verification;
import com.prototest.appdriver.WaitUntil;
import com.prototest.appdriver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;


public interface Element extends WebElement, WrapsElement, Locatable, WrapsDriver{

    default public void highlight(){
        new WebDriver(getWrappedDriver()).highlight(this);
    }

    public String getName();

    public By getBy();

    default public void setText(String value) {
        clear();
        sendKeys(value);
    }

    default public String getHtml(int length)
    {
        try
        {
            String html = this.getAttribute("innerHTML").replace("\r\n","");
            if (html.length() <= length)
                return html;
            int halfLength = length/2;
            String start = html.substring(0, halfLength);
            String end = html.substring((html.length() - halfLength), halfLength);
            return String.format("{0}...{1}", start, end);
        }
        catch (Exception e)
        {
            return "HTML Not found " + e.getMessage();
        }

    }

    public static Element getParent(Element element)
    {

        return (Element) element.findElement(By.xpath(".."));
    }

    default public void clearChecked()
    {
        new WebDriver(getWrappedDriver()).executeJavaScript("arguments[0].checked=false;", this);
    }

    default public void mouseOver()
    {
        Actions action = new Actions(getWrappedDriver()).moveToElement(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        action.build().perform();
    }

    default public String getValue(){
        return getAttribute("value");
    }

    default public Verification verify(int timeoutSec){
        return new Verification(this,timeoutSec);
    }
    default public Verification WaitUntil(int timeoutSec){
        return new Verification(this,timeoutSec);
    }
    default public Verification verify(){
        return new Verification(this, Config.Settings.RuntimeSettings.elementTimeoutSec);
    }
    default public WaitUntil waitUntil() { return new WaitUntil(this, Config.Settings.RuntimeSettings.elementTimeoutSec); }

    default public boolean isDisplayed() {
        if ((isPresent())&&(getWrappedElement().isDisplayed())) return true;
        else
            return false;
    }

    default public boolean isPresent() {
        try{
            return getWrappedElement().isEnabled();
        }catch(Exception e){
            return false;
        }


    }

}
