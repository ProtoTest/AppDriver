package com.prototest.appdriver.pageobjects.wegmans;

import com.prototest.appdriver.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * Created by Brian on 5/27/2014.
 */
public class MyListsPage {
    Element MyListsHeader = new Element("My Lists header button", By.id("android:id/action_bar_title"));
    Element AddToList = new Element("Add List header button", By.name("Add"));
    Element EditList = new Element("Edit List header button", By.name("Edit"));
    Element MoreOptions = new Element("More Options header button", By.name("More Options"));
    Element RecipeHeaderTitle = new Element("Recipe header title text", By.id("com.wegmans.wegmansapp:id/header_title"));

    public MyListsPage VerifyMyListsPageElements()
    {
        Reporter.log("Verifying My Lists page elements.");
        MyListsHeader.Verify.visible();
        AddToList.Verify.visible();
        EditList.Verify.visible();
        MoreOptions.Verify.visible();
        RecipeHeaderTitle.Verify.visible();
        return this;
    }

    public MenuPage OnMyListsPageClickHeader()
    {
        MyListsHeader.click();
        return new MenuPage();
    }

    public MyListsPage CheckItemCheckboxNumber(int itemNum)
    {
        String xpath_str = "//CheckBox[" + itemNum + "]";
        String xpath_log = "List item checkbox number " + itemNum + ".";
        Element ListItemCheckBox = new Element(xpath_log, By.xpath(xpath_str));
       Reporter.log("Clicking list item checkbox number " + itemNum + ".");
        ListItemCheckBox.waitUntilVisible().click();

        String status = ListItemCheckBox.getAttribute("checked");
        Assert.assertEquals(status,"false","Checkbox didn't get checked.");
        return this;
    }

    public MyListsPage UncheckItemCheckboxNumber(int itemNum)
    {
        String xpath_str = "//CheckBox[" + itemNum + "]";
        String xpath_log = "List item checkbox number " + itemNum + ".";
        Element ListItemCheckBox = new Element(xpath_log, By.xpath(xpath_str));
        Reporter.log("Clicking list item checkbox number " + itemNum + ".");
        ListItemCheckBox.waitUntilVisible().click();

        Assert.assertEquals(ListItemCheckBox.getAttribute("checked"),"true","Checkbox didn't get unchecked.");
        Reporter.log("Checkbox has been unchecked.");
        return this;
    }
}
