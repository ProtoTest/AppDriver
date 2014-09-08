package com.prototest.appdriver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Brian on 8/6/2014.
 */
public class CurrentContext {

    public static String getCurrentContext(){
        String name = "";
       for (StackTraceElement ste : Thread.currentThread().getStackTrace()){
           Class thisClass = ste.getClass();

           String fileName = ste.getFileName();
           String className = ste.getClassName();
           String methodName = ste.getMethodName();
           try {
               Class originalClass = Class.forName(className);
               //Logger.info("Class : " + className);
           if(PageObject.class.isAssignableFrom(originalClass) && methodName!= "<init>"){
               String pageObjectName = stripPackageName(className);
               return pageObjectName + "." + methodName;
           }
           if(TestSuite.class.isAssignableFrom(originalClass)&& methodName!= "<init>"){
               String testName = stripPackageName(className);
               return testName + "." + methodName;
           }
           } catch (ClassNotFoundException e) {

           }
       }
        return "";
    }


    /** The string used to separator packages */
    public static final String PACKAGE_SEPARATOR = ".";
    /**
     * Get the short name of the specified class by striping off the package name.
     *
     * @param classname
     *          Class name.
     * @return Short class name.
     */
    public static String stripPackageName(final String classname) {
        int idx = classname.lastIndexOf(PACKAGE_SEPARATOR);

        if (idx != -1)
            return classname.substring(idx + 1, classname.length());
        return classname;
    }
}
