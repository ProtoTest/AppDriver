//package com.prototest.appdriver;
//
//import org.testng.Assert;
//
//public class WaitUntil extends Verification implements Validation {
//    public WaitUntil(Element element, Integer elementTimeoutSec) {
//        this.element = element;
//        this.timeout = elementTimeoutSec;
//
//    }
//    @Override
//    public Validation not(){
//        return new WaitUntil.Not(element,timeout);
//    }
//
//    @Override
//    protected void validateCondition(){
//        if(this.condition){
//            Logger.debug(String.format("WaitUntil Passed : %s", message));
//        }
//        else{
//            Logger.error(String.format("WaitUntil Failed : %s", message));
//            Assert.fail(message);
//        }
//    }
//
//
//    public class Not extends WaitUntil{
//        public Not(Element element, int timeout)
//        {
//            super(element,timeout);
//        }
//
//        @Override
//        protected void validateCondition(){
//            if(!this.condition){
//                Logger.debug(String.format("WaitUntil Passed : %s", message));
//            }
//            else{
//                Logger.error(String.format("WaitUntil Failed : %s", message));
//                Assert.fail(message);
//            }
//        }
//
//    }
//
//}
