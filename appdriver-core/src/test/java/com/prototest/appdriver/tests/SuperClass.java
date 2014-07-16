package com.prototest.appdriver.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Brian on 7/16/2014.
 */
public class SuperClass {
    @DataProvider
    public static Iterator<Object[]> fileDataProvider () {
        List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

        //Populate our List of Object arrays with the file content.

        dataToBeReturned.add(new Object[] { "Firefox" } );
        dataToBeReturned.add(new Object[] { "Chrome" } );
        dataToBeReturned.add(new Object[] { "IE" } );
        //return the iterator - testng will initialize the test class and calls the
        //test method with each of the content of this iterator.
        return dataToBeReturned.iterator();

    }
    String line = null;
    //Constructor: test data in this case String line can be utilized by all the @Test methods.
    @Factory(dataProvider="fileDataProvider")
    public SuperClass(String line) {
        this.line = line;
        // System.out.println("In factory " + line);

    }

    @Test
    public void testDataProvider() {
        //This should print each of the file content one after the other
        //testng calls this method for each line.
        Assert.fail("SampleTest3:testDataProvider(): testData got initialized by the @Factory annotation (via constructor): " + this.line);
    }

    @BeforeClass
    public void beforeClass() {
        // System.out.println("In a method which has beforeClass annotation");
    }

    @AfterClass
    public void afterClass() {
        //System.out.println("In a method which has afterClass annotation");
        // System.out.println("===============================");

    }
}
