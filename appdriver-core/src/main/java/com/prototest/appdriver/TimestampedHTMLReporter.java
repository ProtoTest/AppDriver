package com.prototest.appdriver;

import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 */
public class TimestampedHTMLReporter extends HTMLReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectoryName) {
       // if (Config.timestampHtmlLog) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy__HH_mm");
            File outputDirectory = new File(outputDirectoryName, dateFormat.format(now).toString());
            outputDirectory.mkdir();
            outputDirectoryName = outputDirectory.toString();
            String dir = "test-output\\screenshots\\";
            File screenshotDir = new File(dir);
            File newScreenshotDir = new File(outputDirectoryName + "\\images\\");
            screenshotDir.renameTo(newScreenshotDir);
            screenshotDir.delete();

            System.out.println("HTMLReport : " + outputDirectoryName + "\\index.html");

              //  }


        super.generateReport(xmlSuites, suites, outputDirectoryName);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
