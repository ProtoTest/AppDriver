package com.prototest.appdriver;

import org.openqa.selenium.support.events.WebDriverEventListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 9/9/2014.
 */
public class EventDriver {
    private final List<GolemEventListener> eventListeners =  new ArrayList<GolemEventListener>();
    private final WebDriverEventListener dispatcher = (WebDriverEventListener) Proxy
            .newProxyInstance(
                    WebDriverEventListener.class.getClassLoader(),
                    new Class[]{WebDriverEventListener.class},
                    new InvocationHandler() {
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            try {
                                for (GolemEventListener eventListener : eventListeners) {
                                    method.invoke(eventListener, args);
                                }
                                return null;
                            } catch (InvocationTargetException e) {
                                throw e.getTargetException();
                            }
                        }
                    }
            );


    public EventDriver(){

    }
}
