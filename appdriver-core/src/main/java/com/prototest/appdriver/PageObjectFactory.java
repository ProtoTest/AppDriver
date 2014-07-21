package com.prototest.appdriver;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class PageObjectFactory {
    @Inject
    Injector injector;

    public  <P extends SuperPage> P of(Class<P> pageClazz) {
        try {
            P pageObject = pageClazz.newInstance();
            injector.injectMembers(pageObject);
//            for (Field field : getPageElements(pageObject)) {
//                field.setAccessible(true);
//                try {
//                injector.injectMembers(field.get(pageObject));
//                } catch (Exception e) {
//                    throw new RuntimeException("Failed to inject on Element " + field.getName() + " for page object " + pageClazz.getCanonicalName() + "(" + pageClazz.getSimpleName()+".java)", e);
//                }
//            }
            pageObject.init();
            pageObject.waitForElements();
            return pageObject;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private <P extends SuperPage> List<Field> getPageElements(P pageObject) {

        List<Field> elements = new ArrayList<>(Arrays.asList(pageObject.getClass().getDeclaredFields()));
        elements.removeIf(p -> !p.getType().isAssignableFrom(Element.class));
        return elements;
    }
}
