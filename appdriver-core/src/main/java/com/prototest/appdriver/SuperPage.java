package com.prototest.appdriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 7/15/2014.
 */
public abstract class SuperPage {
    public SuperPage() {
        waitForElements();
    }


    protected abstract void waitForElements();
}