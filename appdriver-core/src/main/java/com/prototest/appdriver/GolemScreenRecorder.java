package com.prototest.appdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.annotations.Test;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GolemScreenRecorder {
    private ScreenRecorder screenRecorder;

    public void startRecording()
    {
        java.io.File folder = new File("test-output\\videos");
        folder.mkdirs();

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle rectangle = new Rectangle(resolution);

        Format fileFormat =  new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI);

        Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                QualityKey, 1.0f,
                KeyFrameIntervalKey, 15 * 60);

                Format mouseFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",FrameRateKey, Rational.valueOf(30));

        Format audioFormat = null;

        try {
            this.screenRecorder = new ScreenRecorder(gc,rectangle,fileFormat,screenFormat,mouseFormat,audioFormat,folder);
            this.screenRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording()
    {
        try {
            this.screenRecorder.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public java.io.File getVideoFile(){
        return this.screenRecorder.getCreatedMovieFiles().get(0);
    }
}
