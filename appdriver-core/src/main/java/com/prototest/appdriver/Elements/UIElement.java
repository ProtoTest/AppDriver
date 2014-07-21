
package com.prototest.appdriver.Elements;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.prototest.appdriver.*;
import com.prototest.appdriver.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//Element class can be instantiated any time but only looks for the element on the page when a function is called
public class UIElement implements Element{
    private By by;
    private WebDriver driver;
    private String name;
    private WebElement element;

    public UIElement(String name, By by) {
        this.name = name;
        this.by = by;
        this.driver = new WebDriver(getWrappedDriver());
    }

    public UIElement(By by, WebElement element) {
        this.name = "Element";
        this.by = by;
        this.element = element;
        this.driver = new WebDriver(getWrappedDriver());
    }

    public UIElement(By by) {
        this.name = "Element";
        this.by = by;
        this.driver = new WebDriver(getWrappedDriver());
    }


    public WebElement getElement()
    {
        if(!isStale())
            return element;
        List<Element> elements = driver.findElements(this.by);
        if(elements.size()==0){
            element = null;
            return null;
        }
        this.element = elements.get(0);
        return this.element;
    }

    public boolean isStale()
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
        try{
            return element.isEnabled();
        }catch(NullPointerException e){
            return false;
        }
        catch(StaleElementReferenceException e){
            return false;
        }

    }

    public String getCssValue(String css) {
        return getElement().getCssValue(css);
    }

    public UIElement setCheckbox(boolean checked) {
        if(getElement().isSelected() != checked) {
            getElement().click();
        }

        return this;
    }

    @Override
    public By getBy() {
        return by;
    }

    // this shit crashes
    public void setText(String text) {
        getElement().clear();
        getElement().sendKeys(text);
    }

    public WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    public boolean isDisplayed() {
        if ((isPresent())&&(element.isDisplayed())) return true;
        else
            return false;
    }

    public boolean isPresent() {
        try{
            return getElement().isEnabled();
        }catch(ElementNotFoundException e) {
            return false;
        }catch(NullPointerException e) {
            return false;
        }


    }

    public List<WebElement> findElements(By by) {
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

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getElement().sendKeys(keysToSend);
    }


    public void sendKeys(String text) {
        getElement().sendKeys(text);
    }

    public UIElement waitUntilVisible() {
        WebDriverWait wait = new WebDriverWait((org.openqa.selenium.WebDriver)driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }

    @Override
    public org.openqa.selenium.WebDriver getWrappedDriver() {
        return TestSuite.getDriver().driver;
    }

    @Override
    public org.openqa.selenium.WebElement getWrappedElement() {
        return element;
    }


    @Override
    public Coordinates getCoordinates() {
       return ((Locatable)element).getCoordinates();
    }

    @Override
    public String getName(){
        return name;
    }
}
