package com.prototest.appdriver;

/**
 * Created by Brian on 9/4/2014.
 */
abstract class ConfigFeature extends AutoInjector {
    public String key;
    public String defaultValue;
    public ConfigFeature(){}
    public ConfigFeature(String key, String defaultValue){
        this.key = key;
        this.defaultValue = defaultValue;
    }

    abstract void startFeature();
    abstract void stopFeature();

}
