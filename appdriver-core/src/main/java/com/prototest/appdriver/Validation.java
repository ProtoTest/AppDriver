package com.prototest.appdriver;

/**
 * Created by Brian on 7/16/2014.
 */
public interface Validation {
    public Validation not();
    public Validation containsText(String text);
    public Validation visible();
    public Validation present();
    public Validation containsValue(String text);
}
