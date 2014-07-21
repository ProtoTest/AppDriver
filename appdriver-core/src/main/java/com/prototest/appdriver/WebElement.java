package com.prototest.appdriver;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;


public abstract class WebElement implements org.openqa.selenium.WebElement, WrapsDriver {
    @Inject
    static Injector injector;
    @Inject
    private Config.Settings.RuntimeSettings config;
    @Inject
    private WebDriverFactory driverFactory;

    protected WebElement() {
        injector.injectMembers(this);
    }

    protected Config.Settings.RuntimeSettings getConfig() {
        return config;
    }
    
    protected WebDriver getDriver() {
        return driverFactory.get();
    }
     public void highlight(){
        getDriver().highlight(this);
    }

     public void setText(String value) {
        clear();
        sendKeys(value);
    }

     public String getHtml(int length)
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

     public void clearChecked()
    {
        getDriver().executeJavaScript("arguments[0].checked=false;", this);
    }

     public void mouseOver()
    {
        Actions action = new Actions(getDriver().driver).moveToElement(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        action.build().perform();
    }

     public String getValue(){
        return getAttribute("value");
    }

//     public WebElement WaitForPresent(WebElement element, By by)
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
