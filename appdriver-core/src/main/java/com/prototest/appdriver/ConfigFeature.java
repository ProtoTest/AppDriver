package com.prototest.appdriver;

/**
 * Created by Brian on 9/4/2014.
 */
abstract class ConfigFeature extends AutoInjector {
    public String key;
    public String defaultValue;
    public String actual;
    public boolean enabled;

    public ConfigFeature(String key, boolean defaultValue){
        this.key = key;
        this.enabled = new Config().getConfigValue(key,defaultValue);
    }

    abstract void startFeature();
    abstract void stopFeature();

}
