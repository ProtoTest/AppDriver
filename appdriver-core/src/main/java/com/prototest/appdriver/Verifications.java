package com.prototest.appdriver;

import org.testng.Assert;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Verifications are like non-terminating assertsions.  This allows us to validate part of the test without stopping the test if the validations fail.
 * A list of all validations is kept, and at the end of the test the VerificationsListener will inject it into the test and validate all validations passed.
 */
public class Verifications {
    class VerificationData{

        /**
         * Create a verification with a specific message, a specific image, and whether or not the verification passed.
         */
        VerificationData(String errorMesage,String imagePath,boolean passed)   {
            this.errorMessage = errorMesage;
            this.imagePath = imagePath;
            this.passed = passed;
        }
        /**
         * Create a verification with a specific message, and whether or not the verification passed.
         */
        VerificationData(String errorMesage, boolean passed)   {
            this.errorMessage = errorMesage;
            this.imagePath = "";
            this.passed = passed;
        }
        public String errorMessage;
        public String imagePath;
        public boolean passed;
    }

    /**
     * The list of verifications kept during the test.  After the test is finished this list will be checked to validate all verifications passed.
     */
    private List<VerificationData> verifications = new LinkedList<VerificationData>();


    public List<VerificationData> getVerifications(){
         return verifications;
    }

    public void clearVerifications(){
        verifications = new LinkedList<VerificationData>();
    }

    /**
     * Checks the list of verifications and fails the test if any of them did not pass.  Use this to fail a test for failed verificaitons.
     */
    public boolean assertVerifications(){
        String failed = getFailures();
        if(failed.length()>0){
            //Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            //Reporter.getCurrentTestResult().setThrowable(new AssertionFailedError(String.format("The test failed due to %s verification errors",numFailed)));
            Assert.fail(String.format("The test failed due to verification errors:\n%s", failed));
            return false;
        }
        return true;
    }

    /**
     * Add a verification to the list, using a specified message, and whether or not it passed.
     */
    public void addVerification(String message,boolean passed){
        if(passed){
            Logger.debug(String.format("Verification Passed : %s", message));
            verifications.add(new VerificationData(message,true));
        }
        else{
            Logger.error(String.format("Verification Failed : %s", message));
            Logger.screenshot();
            verifications.add(new VerificationData(message, "", false));
        }
    }

    /**
     * Add a verification to the list, using a specified message, a path to a screenshot, and whether or not it passed.
     */
    public void addVerification(String message, String filePath, boolean passed){
        if(passed){
            Logger.debug(String.format("Verification Passed : %S", message));
        }
        else{
            Logger.error(String.format("Verification Failed : %S", message));
            Logger.image(new File(filePath));
        }
        verifications.add(new VerificationData(message,filePath,false));

    }

    /**
     * Get the list of failed verifications in a string.  
     */
    public String getFailures() {
        StringBuilder errors = new StringBuilder();
        for (VerificationData verification : verifications) {
            if (!verification.passed) {
                errors.append("\t"+verification.errorMessage);
            }

        }
        return errors.toString();
    }
}

