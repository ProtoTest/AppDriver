package com.prototest.appdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

interface WebElement extends org.openqa.selenium.WebElement {
    WebDriver driver= BrowserTestSuite.driver;
    default public void highlight(){
        BrowserTestSuite.driver.highlight(this);
    }

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

    public static WebElement getParent(WebElement element)
    {

        return (WebElement) element.findElement(By.xpath(".."));
    }

    default public void clearChecked()
    {
        driver.executeJavaScript("arguments[0].checked=false;", this);
    }

    default public void mouseOver()
    {
        Actions action = new Actions(driver.driver).moveToElement(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        action.build().perform();
    }

    default public void sendKeys(CharSequence... chars){
        sendKeys(chars.toString());
    }

//    default public WebElement WaitForPresent(WebElement element, By by)
//    {
//        Integer timeout = Config.Settings.RuntimeSettings.elementTimeoutSec;
//        Calendar then = Calendar.getInstance();
//        then.add(Calendar.SECOND,timeout);
//        for (Calendar now = Calendar.getInstance(); now.before(then); now = Calendar.getInstance())
//        {
//            List<org.openqa.selenium.WebElement> eles = element.findElements(by);
//            if (eles.size() > 0)
//                return (WebElement)eles.get(0);
//        }
//        throw new NoSuchElementException(String.format("Element (%s) was not present after %s seconds",
//                by.toString(), timeout));
//    }
}
