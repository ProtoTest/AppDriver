package com.prototest.appdriver;

import org.openqa.selenium.WebDriver;

/**
 * Created by Brian on 7/7/2014.
 */
public class TestContainer {
    public Config config;
    public WebDriver driver;
    public Logger logger;
    public String name;
    public TestContainer(String name){
        this.driver = null;
        this.config = new Config();
        this.logger = new Logger();
        this.name = name;
    }
}
