package com.prototest.appdriver.tests;

import junit.framework.Assert;

/**
 * Created by Brian on 7/18/2014.
 */
public class Test {

    private interface i1{
      public void one();
    }

    private interface i2 extends i1{
      public void two();
    }

    public class class1 implements i1{

        @Override
        public void one() {
            Assert.fail("1");
        }
    }

    public class class2 implements i2{
        @Override
        public void one() {
            Assert.fail("one");
        }

        @Override
        public void two() {
            Assert.fail("two");
        }
    }


    @org.testng.annotations.Test
    public void test(){
        class1 c1 = new class1();
        class2 c2 = new class2();
        ((i1)c2).one();
    }
}
