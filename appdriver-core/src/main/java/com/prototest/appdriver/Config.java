package com.prototest.appdriver;

import com.google.inject.Inject;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.ArrayList;

/**
 * Config (Configuration settings) class. Used to load up appdriver and test specific configuration
 *  values from a file. Uses apache-commons library to facilitate property management.
 *
 *  Example usage: Config.Settings.RuntimeSettings.launchBrowser
 *
 * @author Matt Siwiec
 */
public final class Config {
    private Configuration config;
    private static final String CONFIG_FILE_NAME = "config.properties";

    // static constructor
     {
        try {
//            Logger.info("Loading test configuration file: " + CONFIG_FILE_NAME);
            config = new PropertiesConfiguration(CONFIG_FILE_NAME);
        } catch (ConfigurationException e) {
//            Logger.error("Caught ConfigurationException in: " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     *  Returns the configuration file value for 'key' passed in. If the 'key' is not found, then 'default_value'
     *  is returned.
     *
     * @param key               Configuration file key string
     * @param default_value     Default value returned, cannot be null
     * @return <T>              Generic type value defined in configuration file,
     *                          or default value, if not found in config file.
     * @throws NullPointerException
     * @throws IllegalArgumentException
     *
     * <p>
     *     Note: You cannot use primitive data types (i.e. int, boolean, etc.) with generics. When defining
     *           variables to store this config data use the object instead of the primitive
     *           (i.e. Boolean instead of boolean).
     *      Suppressing warning for the cast to generic type T.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public  <T> T getConfigValue(String key, T default_value) throws NullPointerException, IllegalArgumentException {
        T ret_val = default_value;

        if(key == null) throw new NullPointerException("param 'key' can not be null!");
        if(default_value == null) throw new NullPointerException("param 'default_value' can not be null!");

        if(config != null) {
            if(default_value instanceof Integer) {
                ret_val = (T)config.getInteger(key, (Integer)default_value);
            } else if(default_value instanceof String) {
                ret_val = (T)config.getString(key);
            } else if(default_value instanceof Boolean) {
                ret_val = (T)config.getBoolean(key, (Boolean)default_value);
            } else if(default_value instanceof String[]) {
                ret_val = (T)config.getStringArray(key);
            }
            else {
                throw new IllegalArgumentException(default_value.getClass() + " is not supported by this function");
            }
        }

        return ret_val == null ? default_value : ret_val;
    }

    /**
     * Settings class: Wraps all of the test and appdriver configuration settings
     */
    public static class Settings {

        public static class RuntimeSettings {
            private final Config config;
            public BrowserManager.Browser browser;
            public Boolean launchBrowser;
            public Integer testTimeoutMin;
            public Integer elementTimeoutSec;
            public Integer pageTimeoutSec;
            public String  environmentURL;
            public Integer degreeOfParallelism;
            public Integer commandDelayMs;
            public Boolean runOnRemoteHost;
            public String  hostIP;
            public int logLevel;



            @Inject
            public RuntimeSettings(Config config) {
                try {
                    this.config = config;
                    browser = getBrowser();
                    //
                    launchBrowser = config.getConfigValue("launchBrowser", true);
                    testTimeoutMin = config.getConfigValue("testTimeoutMin", 5);
                    elementTimeoutSec = config.getConfigValue("elementTimeoutSec", 20);
                    pageTimeoutSec = config.getConfigValue("pageTimeoutSec", 30);
                    environmentURL = config.getConfigValue("environmentURL", "");
                    degreeOfParallelism = config.getConfigValue("degreeOfParallelism", 5);
                    commandDelayMs = config.getConfigValue("commandDelayMs", 0);
                    runOnRemoteHost = config.getConfigValue("runOnRemoteHost", false);
                    hostIP = config.getConfigValue("hostIp", "localhost");
                    logLevel = config.getConfigValue("logLevel",2);
                } catch (NullPointerException e) {
//                    Logger.error(e.getMessage());
                    throw new RuntimeException(e);
                } catch (IllegalArgumentException e) {
//                    Logger.error(e.getMessage());
                    throw new RuntimeException(e);

                }
            }

            private BrowserManager.Browser getBrowser() {
                return BrowserManager.Browser.fromString(config.getConfigValue("browser", "chrome"));
            }

            private ArrayList<BrowserManager.Browser> getBrowserList() {
                ArrayList<BrowserManager.Browser> browsers = new ArrayList<BrowserManager.Browser>();
                String[] browserConfigList = {};
                browserConfigList = config.getConfigValue("browsers", browserConfigList);
                if(browserConfigList != null) {
                    for(String browser_str: browserConfigList) {
                        // do not test on IE if the OS is not Windows
                        if(browser_str.equalsIgnoreCase("IE") && !System.getProperty("os.name").startsWith("Windows")) {
                            continue;
                        }

                        browsers.add(BrowserManager.Browser.valueOf(browser_str));
                    }
                }

                return browsers;
            }
        }

//        public static class ReportSettings {
//            public static Boolean htmlOnError;
//            public static Boolean screenShotOnError;
//            public static Boolean videoRecordingOnError;
//            public static Boolean commandLogging;
//            public static Boolean actionLogging;
//            public static Boolean spellChecking;
//
//            static {
//                try {
//                    htmlOnError = Config.getConfigValue("htmlOnError", true);
//                    screenShotOnError = Config.getConfigValue("screenshotOnError", true);
//                    videoRecordingOnError = Config.getConfigValue("videoRecordingOnError", true);
//                    commandLogging = Config.getConfigValue("commandLogging", true);
//                    actionLogging = Config.getConfigValue("actionLogging", true);
//                    spellChecking = Config.getConfigValue("spellChecking", true);
//                } catch (NullPointerException e) {
//                    Logger.error(e.getMessage());
//                } catch (IllegalArgumentException e) {
//                    Logger.error(e.getMessage());
//                }
//            }
//        }
//
//        // TODO: Need to find a Fiddler Proxy java equivalent
//        public static class HttpProxySettings {
//            public static Boolean startProxy;
//            public static Integer proxyPort;
//            public static Integer sslProxyPort;
//
//            // static class constructor
//            static {
//                try {
//                    startProxy = Config.getConfigValue("startWHATSMYNAMEProxy", true);
//                    proxyPort = Config.getConfigValue("proxyPort", 8876);
//                    sslProxyPort = Config.getConfigValue("sslProxyPort", 7777);
//                } catch (NullPointerException e) {
//                    Logger.error(e.getMessage());
//                } catch (IllegalArgumentException e) {
//                    Logger.error(e.getMessage());
//                }
//            }
//        }
//
//        public static class LocalProxySettings {
//            public static Boolean localProxy;
//            public static Integer localPort;
//            public static String localHost;
//
//            // static class constructor
//            static {
//                try {
//                    localProxy = Config.getConfigValue("useLocalProxy", false);
//                    localPort = Config.getConfigValue("proxyPort", 8888);
//                    localHost = Config.getConfigValue("hostIP", "localhost");
//                } catch (NullPointerException e) {
//                    Logger.error(e.getMessage());
//                } catch (IllegalArgumentException e) {
//                    Logger.error(e.getMessage());
//                }
//            }
//        }
//
//        public static class AppiumSettings {
//            public static Boolean launchApp;
//            public static String appPath;
//            public static String appPackage;
//            public static String appActivity;
//            public static String deviceName;
//            public static String platformName;
//            public static String platformVersion;
//            public static boolean resetApp;
//
//            // static class constructor
//            static {
//                try {
//                    launchApp = Config.getConfigValue("lauchApp", false);
//                    appPath = Config.getConfigValue("appPath", "");
//                    appPackage = Config.getConfigValue("appPackage", "");
//                    appActivity = Config.getConfigValue("appActivity", "");
//                    deviceName = Config.getConfigValue("deviceName", "Android");
//                    platformName = Config.getConfigValue("platformName", "");
//                    platformVersion = Config.getConfigValue("platFormVersion", "");
//                    resetApp = Config.getConfigValue("resetApp", true);
//                } catch (NullPointerException e) {
//                    Logger.error(e.getMessage());
//                } catch (IllegalArgumentException e) {
//                    Logger.error(e.getMessage());
//                }
//            }
//        }
    }
}




