package com.prototest.appdriver;

import com.prototest.appdriver.Elements.Element;
import com.prototest.appdriver.Elements.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.util.*;
import java.util.NoSuchElementException;

public class WebDriver {

    public EventFiringWebDriver driver;
    private By by;

    public WebDriver(org.openqa.selenium.WebDriver driver){
        this.driver = new EventFiringWebDriver(driver);
        RegisterEvents();
    }

    private void RegisterEvents() {
        driver.register(new WebDriverEventListener() {
            @Override
            public void beforeNavigateTo(String s, org.openqa.selenium.WebDriver webDriver) {
                Logger.debug(String.format("Navigating to %s", s));
            }

            @Override
            public void afterNavigateTo(String s, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void beforeNavigateBack(org.openqa.selenium.WebDriver webDriver) {
                Logger.debug(String.format("Navigating back"));
            }

            @Override
            public void afterNavigateBack(org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void beforeNavigateForward(org.openqa.selenium.WebDriver webDriver) {
                Logger.debug(String.format("Navigating Forward"));
            }

            @Override
            public void afterNavigateForward(org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void beforeFindBy(By by, org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                try{
                    String name = ((Element)webElement).getName();
                    Logger.debug(String.format("Finding %s %s",name,by.toString()));
                }catch(Exception e){
                    Logger.error(e.getMessage());
                }

            }

            @Override
            public void afterFindBy(By by, org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                highlight(webElement);
            }

            @Override
            public void beforeClickOn(org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                Logger.debug(String.format("Clicking Element %s",webElement));
            }

            @Override
            public void afterClickOn(org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void beforeChangeValueOf(org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                Logger.debug(String.format("Typing into Element %s",webElement));
            }

            @Override
            public void afterChangeValueOf(org.openqa.selenium.WebElement webElement, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void beforeScript(String s, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void afterScript(String s, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onException(Throwable throwable, org.openqa.selenium.WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void get(String s) {
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<Element> findElements(By by) {
        List<WebElement> elements = driver.findElements(by);

       ArrayList<Element> enhancedElements = new ArrayList<Element>();
        for(org.openqa.selenium.WebElement element : elements){
            enhancedElements.add((Element)element);
        }
        return enhancedElements;
    }

    public Element findElement(By by) {
        return new UIElement(by,driver.findElement(by));
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public org.openqa.selenium.WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public org.openqa.selenium.WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public org.openqa.selenium.WebDriver.Options manage() {
        return driver.manage();
    }

    public org.openqa.selenium.WebElement hide(org.openqa.selenium.WebElement element)
    {
        return
                (org.openqa.selenium.WebElement) executeJavaScript("arguments[0].style.visibility='hidden';return;", element);
    }

    public org.openqa.selenium.WebElement show(org.openqa.selenium.WebElement element)
    {
        return
                (org.openqa.selenium.WebElement)executeJavaScript("arguments[0].style.visibility='visible';return;", element);
    }

//    public static IWebElement FindInSiblings(this IWebElement element, By by)
//    {
//        return element.GetParent().FindElement(by);
//    }
//
//    public static IWebElement FindInChildren(this IWebElement element, By by)
//    {
//        return element.FindElement(by);
//    }
//
    public static Element getParent(Element element)
    {

        return (Element) element.findElement(By.xpath(".."));
    }

//    public static string GetHtml(this IWebElement element)
//    {
//        return element.GetAttribute("outerHTML");
//    }
//
    public static String getHtml(Element element, int length)
    {
        try
        {
            String html = element.getAttribute("innerHTML").replace("\r\n","");
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
//
    public boolean isStale(org.openqa.selenium.WebElement element)
    {
        try
        {
            boolean enabled = element.isEnabled();
            return false;
        }
        catch (Exception e)
        {
            return true;
        }
    }

    public void highlight(org.openqa.selenium.WebElement element)
    {
        try
        {
            String originalElementBorder = (String) executeJavaScript("return arguments[0].style.border", element);
            executeJavaScript("arguments[0].style.border='3px solid red'; return;", element);
            Thread.sleep(20);
            executeJavaScript("arguments[0].style.border='" + originalElementBorder + "'; return;", element);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
        }
    }

    /// <summary>
    ///     Clear a checked element (radio or checkbox)
    /// </summary>
    /// <param name="element"></param>
    public void clearChecked(org.openqa.selenium.WebElement element)
    {

       executeJavaScript("arguments[0].checked=false;", element);
    }

    public void mouseOver(org.openqa.selenium.WebElement element)
    {
        Actions action = new Actions(driver).moveToElement(element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        action.build().perform();
    }

    public Element WaitForPresent(Element element, By by)
    {
        Integer timeout = Config.Settings.RuntimeSettings.elementTimeoutSec;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND,timeout);
        for (Calendar now = Calendar.getInstance(); now.before(then); now = Calendar.getInstance())
        {
            List<org.openqa.selenium.WebElement> eles = element.findElements(by);
            if (eles.size() > 0)
                return (Element)eles.get(0);
        }
        throw new NoSuchElementException(String.format("Element (%s) was not present after %s seconds",
                by.toString(), timeout));
    }
//
//    public static IWebElement WaitForPresent(this IWebDriver driver, By by, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.ElementTimeoutSec;
//        var then = DateTime.Now.AddSeconds(timeout);
//        for (var now = DateTime.Now; now < then; now = DateTime.Now)
//        {
//            var eles = driver.FindElements(by);
//            if (eles.Count > 0)
//                return eles[0];
//            Common.Delay(1000);
//        }
//        throw new NoSuchElementException(string.Format("Element ({0}) was not present after {1} seconds",
//                by.ToString(), timeout));
//    }
//
//    public static void WaitForNotPresent(this IWebDriver driver, By by, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.ElementTimeoutSec;
//        var then = DateTime.Now.AddSeconds(timeout);
//        for (var now = DateTime.Now; now < then; now = DateTime.Now)
//        {
//            var eles = driver.FindElements(by);
//            if (eles.Count > 0)
//                return;
//            Common.Delay(1000);
//        }
//        throw new InvalidElementStateException(string.Format("Element ({0}) was still present after {1} seconds",
//                by.ToString(), timeout));
//    }
//
//    public static IWebElement WaitForVisible(this IWebDriver driver, By by, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.ElementTimeoutSec;
//        var then = DateTime.Now.AddSeconds(timeout);
//        for (var now = DateTime.Now; now < then; now = DateTime.Now)
//        {
//            var eles = driver.FindElements(by);
//            if (eles.Count > 0 && eles[0].Displayed)
//                return eles[0];
//        }
//        throw new ElementNotVisibleException(string.Format("Element ({0}) was not visible after {1} seconds",
//                by.ToString(), timeout));
//    }
//
//    public static void WaitForNotVisible(this IWebDriver driver, By by, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.ElementTimeoutSec;
//        var then = DateTime.Now.AddSeconds(timeout);
//        for (var now = DateTime.Now; now < then; now = DateTime.Now)
//        {
//            var eles = driver.FindElements(by);
//            if (eles.Count > 0 && !eles[0].Displayed)
//                return;
//            Common.Delay(1000);
//        }
//        throw new ElementNotVisibleException(string.Format("Element ({0}) was still visible after {1} seconds",
//                by.ToString(), timeout));
//    }
//
//    public static IWebElement FindElementWithText(this IWebDriver driver, string text)
//    {
//        return driver.FindElement(By.XPath("//*[text()='" + text + "']"));
//    }
//
//    public static IWebElement WaitForElementWithText(this IWebDriver driver, string text, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.ElementTimeoutSec;
//        return driver.WaitForPresent(By.XPath("//*[text()='" + text + "']"));
//    }
//
//    public static void VerifyElementPresent(this IWebDriver driver, By by, bool isPresent = true)
//    {
//        int count = driver.FindElements(by).Count;
//        verify(isPresent && count == 0, "VerifyElementPresent Failed : Element : " + @by +
//            (isPresent ? " found" : " not found"));
//    }
//
//    public static void verify(bool condition, string message)
//    {
//        if (!condition)
//        {
//            TestBase.AddVerificationError(message);
//        }
//        else
//        {
//            TestContext.CurrentContext.IncrementAssertCount();
//        }
//    }
//
//    public static IWebElement SelectOption(this IWebElement element, string option)
//    {
//        new SelectElement(element).SelectByText(option);
//        return element;
//    }
//
//    public static IWebElement SelectOptionByPartialText(this IWebElement element, string text)
//    {
//        var s_element = new SelectElement(element);
//
//        foreach (IWebElement option in s_element.Options.Where(option => option.Text.Contains(text)))
//        {
//            option.Click();
//            break;
//        }
//
//        return element;
//    }
//
//    public static IWebElement FindVisibleElement(this IWebDriver driver, By by)
//    {
//        ReadOnlyCollection<IWebElement> elements = driver.FindElements(by);
//        foreach (IWebElement ele in elements.Where(ele => (ele.Displayed) && (ele.Enabled)))
//        {
//            return ele;
//        }
//        throw new ElementNotVisibleException("No element visible for : " + @by);
//    }
//
//    public static void VerifyElementVisible(this IWebDriver driver, By by, bool visible = true)
//    {
//        ReadOnlyCollection<IWebElement> elements = driver.FindElements(by);
//        int count = elements.Count;
//        bool visible = false;
//        if (visible && count != 0)
//        {
//            foreach (IWebElement element in elements)
//            {
//                if (element.Displayed)
//                {
//                    visible = true;
//                }
//            }
//        }
//        verify(visible != visible,
//                "VerifyElementVisible Failed : Element : " + @by +
//            (visible ? " visible" : " not visible"));
//    }
//
//    public static void VerifyElementText(this IWebDriver driver, By by, string expectedText)
//    {
//        string actualText = driver.FindElement(by).Text;
//        verify(actualText != expectedText,
//                "VerifyElementText Failed : Expected : " + @by + " Expected text : '" + expectedText +
//            "' + Actual '" + actualText);
//    }
//
//    public static Rectangle GetRect(this IWebElement element)
//    {
//        try
//        {
//            var jsDriver = ((IJavaScriptExecutor) WebDriverTestBase.driver);
//            var originalElementBorder = (string) jsDriver.ExecuteScript("return arguments[0].style.border", element);
//            return (Rectangle) jsDriver.ExecuteScript("return arguments[0].getBoundingClientRect();", element);
//        }
//        catch (Exception)
//        {
//            return new Rectangle();
//        }
//    }
//
//    public static Image GetScreenshot(this IWebDriver driver)
//    {
//        Image screen_shot = null;
//
//        try
//        {
//            if (driver == null) return screen_shot;
//            Screenshot ss = ((ITakesScreenshot) driver).GetScreenshot();
//            var ms = new MemoryStream(ss.AsByteArray);
//            screen_shot = Image.FromStream(ms);
//            ms.Dispose();
//
//        }
//        catch (Exception e)
//        {
//            TestLog.Failures.WriteLine("Failed to take screenshot: " + e.Message);
//        }
//
//        return screen_shot;
//    }
//
//    public static void SetText(this IWebElement element, string text)
//    {
//        element.Clear();
//        element.SendKeys(text);
//        if (element.GetAttribute("value") != text)
//        {
//            element.Clear();
//            element.SendKeys(text);
//        }
//    }
//
    public Object executeJavaScript(String script)
    {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("return " + script);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Object executeJavaScript(String script, Object... params)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, params);
    }

    public File getScreenshot(){
        String dir = "test-output\\screenshots\\";
        File report = new File(dir);
        report.mkdir();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File newScreenshot = new File(dir + screenshot.getName());
        screenshot.renameTo(newScreenshot);
        return newScreenshot;
    }
//
//    public static void JavaWindowScroll(this IWebDriver driver, int xCord, int yCord)
//    {
//        var js = (IJavaScriptExecutor) driver;
//        js.ExecuteScript("window.scrollBy(" + xCord + "," + yCord + ");");
//    }
//
//    public static IWebElement ScrollIntoView(this IWebElement element)
//    {
//        var js = (IJavaScriptExecutor) WebDriverTestBase.driver;
//        js.ExecuteScript("arguments[0].scrollIntoView(); return;", element);
//        return element;
//    }
//
//    public static void SelectNewWindow(this IWebDriver driver, int timeout = 0)
//    {
//        if (timeout == 0) timeout = Config.Settings.runTimeSettings.OpenWindowTimeoutSec;
//
//        try
//        {
//            string currentHandle = driver.CurrentWindowHandle;
//            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(timeout));
//            wait.Until(d => (driver.WindowHandles.Count() > 1));
//
//            foreach (string handle in (driver.WindowHandles))
//            {
//                if (handle != currentHandle)
//                {
//                    driver.SwitchTo().Window(handle);
//                }
//            }
//        }
//        catch (Exception)
//        {
//        }
//    }
//
//    public static void SetOrientation(this IWebDriver driver, ScreenOrientation orientation)
//    {
//        ((IRotatable) driver).Orientation = orientation;
//    }
//
//    public static void SetBrowserSize(this IWebDriver driver, Size size)
//    {
//        driver.Manage().Window.Size = size;
//    }
//
//    public static IWebDriver GetWrappedDriver(this IWebElement element)
//    {
//        return ((IWrapsDriver) element).WrappedDriver;
//    }
//
//    public static IWebElement DragToOffset(this IWebElement element, int x, int y)
//    {
//        var action = new Actions(WebDriverTestBase.driver);
//        action.MoveToElement(element, x, y).Click().Build().Perform();
//        return element;
//    }
//
//    public static Image GetImage(this IWebElement element)
//    {
//        var size = new Size(element.Size.Width, element.Size.Height);
//        if (element.Displayed == false || element.Location.X<0 || element.Location.Y <0)
//        {
//            throw new BadImageFormatException(string.Format(
//                    "Could not create image for element as it is hidden"));
//        }
//        var cropRect = new Rectangle(element.Location, size);
//        using (Image screenShot = TestBase.testData.driver.GetScreenshot())
//        {
//            if (cropRect.X < 0)
//            {
//                cropRect.X = 0;
//
//            }
//            if (cropRect.Y < 0)
//            {
//                cropRect.Y = 0;
//
//            }
//            if (cropRect.X + cropRect.Width > screenShot.Width)
//            {
//                cropRect.Width = screenShot.Width - cropRect.X;
//            }
//            if (cropRect.Y + cropRect.Height > screenShot.Height)
//            {
//                cropRect.Height = screenShot.Height - cropRect.Y;
//            }
//
//            try
//            {
//                var bmpImage = new Bitmap(screenShot);
//                Bitmap bmpCrop = bmpImage.Clone(cropRect, bmpImage.PixelFormat);
//                return bmpCrop;
//
//            }
//            catch (Exception e)
//            {
//                return null;
//            }
//
//        }

}
