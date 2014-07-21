package com.prototest.appdriver;


public class TestContainer {
    public Config config;
    public WebDriver driver;
    public Logger logger;
    public String name;
    public Verifications verifications;
    public TestContainer(String name){
        this.driver = null;
        this.config = new Config();
        this.logger = new Logger();
        this.name = name;
        this.verifications = new Verifications();
    }
    public TestContainer(String name,WebDriver driver){
        this.driver = driver;
        this.config = new Config();
        this.logger = new Logger();
        this.name = name;
        this.verifications = new Verifications();
    }
}
