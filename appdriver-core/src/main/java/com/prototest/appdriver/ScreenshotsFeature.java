package com.prototest.appdriver;

public class ScreenshotsFeature extends ConfigFeature {
    public ScreenshotsFeature(String key, Boolean value) {
        super(key,value);
    }

    @Override
    void startFeature() {
        logger.screenshot();
    }

    @Override
    void stopFeature() {

    }
}
