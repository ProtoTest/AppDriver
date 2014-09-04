package com.prototest.appdriver;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class VideoRecordingListener extends AutoInjector implements IHookable  {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        TestSuite.recorder.startRecording();
        callBack.runTestMethod(testResult);
        TestSuite.recorder.stopRecording();
        logger.info("<a href=\"..\\videos\\" + TestSuite.recorder.getVideoFile().getName() + "\"/>video</a>");
    }
}
