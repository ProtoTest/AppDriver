package com.prototest.appdriver;

import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        text = String.format("----! WARNING: (%s) %s", timestamp, text);
        System.out.println(text);
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log(String.format("<div style=\"background-color:yellow\">%s</div>", text));
    }
    /**
     * Log an Error.  Used when something went terminally wrong, but test execution doesn't need to be stopped.  Will show up red in the HTML log.
     */
    public static void error(String text) {
        if (Config.Settings.RuntimeSettings.logLevel < 0) return;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss SSS");
        String timestamp = sdf.format(date);
        text = String.format("!---- ERROR: (%s) %s", timestamp, text);
        System.out.println(text);
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log(String.format("<div style=\"background-color:red; color:white\">%s</div>", text));
    }

    /**
     * Capture a screenshot and include it in the log, using a random file name.
     */
    public static void screenshot() {
        screenshot("", null);
    }


    /**
     * Capture a screenshot and include it in the log, using a random file name.
     * Highlight the given search rectangle, to show where the search was looking.
     */
    public static void screenshot(Rectangle drawRectangle) {
        screenshot("", drawRectangle);
    }


    /**
     * Capture a screenshot and include it in the log, using the specified file path.
     */
    public static void screenshot(String name) {
        screenshot(name, null);
    }

    /**
     * Capture a screenshot and include it in the log, using the specified file path.
     * Highlight the given search rectangle, to show where the search was looking.
     */
    public static void screenshot(String name, Rectangle drawRectangle) {

    }

    /**
     * Log an image to the report that already exists, using a specific file name.
     */
    public static void image(File image) {
        if (image.exists())
            Reporter.log(String.format("<img src=\"%s\"/>", image.getAbsolutePath()));
        else
            warning("Could not log image, as the path was incorrect : " + image.getAbsolutePath());
    }

    /**
     * Log a list of images paths to the HTML report.  They should already exist.
     */
    public static void images(File[] images) {
        String outputHtml = "<div>";

        for (File image : images) {
            if (image.exists()) {
                outputHtml += String.format("<img src=\"%s\"/>", image.getAbsolutePath());
            } else
                warning("Could not log image, as the path was incorrect : " + image.getAbsolutePath());
        }
        outputHtml += "</div>";
        Reporter.log(outputHtml);

    }

}
