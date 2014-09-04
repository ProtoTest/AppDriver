package com.prototest.appdriver;

import com.google.inject.Inject;

/**
 * Created by Brian on 9/4/2014.
 */
public class AutoInjector {
    @Inject
    Config.Settings.RuntimeSettings config;
    @Inject
    Logger logger;
    @Inject
    static com.google.inject.Injector injector;
    public AutoInjector(){
        injector.injectMembers(this);
    }


}
