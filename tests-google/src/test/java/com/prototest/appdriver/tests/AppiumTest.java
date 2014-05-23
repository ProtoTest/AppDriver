package com.prototest.appdriver.tests;

import com.prototest.appdriver.AppiumTestBase;
import com.prototest.appdriver.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Brian on 5/23/2014.
 */
public class AppiumTest extends AppiumTestBase {
    @Test
    public void test(){
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsUser("testpc13@wegmans.com","wegmans1");
    }
}
