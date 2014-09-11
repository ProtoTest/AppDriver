package com.prototest.appdriver;

public class VideoRecordingFeature extends ConfigFeature {
    public VideoRecordingFeature(String key, Boolean value) {
        super(key,value);
    }

    @Override
    void startFeature() {
        TestSuite.recorder.startRecording();
    }

    @Override
    void stopFeature() {
        TestSuite.recorder.stopRecording();
        logger.info("<a href=\"..\\videos\\" + TestSuite.recorder.getVideoFile().getName() + "\"/>video</a>");
    }
}
