package com.prototest.appdriver;


import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * This class contains global logging methods, using the config file to configure level and format.
 */
public class Logger {

    static {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    /**
     * Log Info, this is the highest level of logging. Should be used for logging test intent, and actions.
     */
    public static void info(String text) {
        if (Config.Settings.RuntimeSettings.logLevel< 1) return;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("INFO: [%s] %s", timestamp, text);
        System.out.println(text);
        Reporter.log(String.format("<div style=\"color:Blue\">%s</div>", text));
    }

    /**
     * Log Debug, this is designed for debug information, used to determine why a test is failing.
     */
    public static void debug(String text) {
        if (Config.Settings.RuntimeSettings.logLevel < 2) return;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("DEBUG: [%s] %s", timestamp, text);
        System.out.println(text);
        Reporter.log(String.format("<div>%s</div>", text));
    }

    /**
     * Log A Warning.  Use this when something went wrong, but may not be critical for test success.  Will show up yellow in the report.
     */
    public static void warning(String text) {
        if (Config.Settings.RuntimeSettings.logLevel < 1) return;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("WARNING: (%s) %s", timestamp, text);
        System.out.println("----> " + text);
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log(String.format("<div style=\"background-color:yellow\">%s</div>", text));
    }

    public static void error(String text) {
        if (Config.Settings.RuntimeSettings.logLevel < 0) return;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("ERROR: (%s) %s", timestamp, text);
        System.out.println("!---- " + text);
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log(String.format("<div style=\"background-color:red; color:white\">%s</div>", text));
    }

    public static void screenshot(String text) {
        File screenshot = TestSuite.getDriver().getScreenshot();
        String path = getLocalPath(screenshot);
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("SCREENSHOT: [%s] %s", timestamp, text);
        System.out.println(text);
        Reporter.log(String.format("<div>%s</div>", text));
        Reporter.log(String.format("<img src=\"%s\"/>", path));

    }

    public static void screenshot() {
        File screenshot = TestSuite.getDriver().getScreenshot();
        String path = getLocalPath(screenshot);
        Reporter.log(String.format("<img src=\"%s\"/>", path));
    }

    public static void image(File image) {
        if (image.exists()) {
            String newPath = getLocalPath(image);
            Reporter.log(String.format("<img src=\"%s\"/>", newPath));
        }
        else
            warning("Could not log image, as the path was incorrect : " + image.getAbsolutePath());
    }


    public static void images(File[] images) {
        String outputHtml = "<div>";

        for (File image : images) {
            if (image.exists()) {
                String newPath = getLocalPath(image);
                outputHtml += String.format("<img src=\"%s\"/>", newPath);
            } else
                warning("Could not log image, as the path was incorrect : " + image.getAbsolutePath());
        }
        outputHtml += "</div>";
        Reporter.log(outputHtml);

    }

    private static String getLocalPath(File file){
        return "..\\images\\" + file.getName();
    }
}
