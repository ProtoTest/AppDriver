package com.prototest.appdriver;

import com.prototest.appdriver.Elements.Element;

public class Verification implements Validation{
    protected Element element;
    protected boolean condition;
    protected String message;
    protected String conditional;
    protected int timeout;

    public Verification(){}

    public Verification(Element element, int timeout){
        this.element = element;
        this.timeout=timeout;
    }

    public Validation not(){
        return new Verification.Not(element,timeout);
    }

    void validateCondition(){
        TestSuite.getTest().verifications.addVerification(this.message,this.condition);
    }

    protected String getConditional(){
        if(this.condition){
            return "";
        }
        else
            return " not";
    }
    protected String getConditional(boolean condition){
        if(condition){
            return "";
        }
        else
            return " not";
    }
    protected boolean waitForElement(){

        for(int i=0;i<this.timeout;i++){
            if(element.isPresent()) {
                this.message = String.format("%s (%s) is%s present", element.getName(),element.getBy(), getConditional(true));
                return true;
            }
            else
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
        }
        this.message = String.format("%s (%s) is%s present",element.getName(),element.getBy(), getConditional(false));
        return false;
    }

    public Verification containsText(String text)
    {
        this.condition = waitForElement();
        if(this.condition){
            
            String pageText = this.element.getText();
            this.condition = pageText.contains(text);
            this.message = String.format("%s (%s) does%s contain text '%s'",element.getName(),element.getBy(), getConditional(), text);
        }
       // this.condition = pageText.contains(text);
        validateCondition();
        return this;
    }

    public Verification containsValue(String text)
    {
        this.condition = waitForElement();
        if(this.condition){

            String pageText = this.element.getValue();
            this.condition = pageText.contains(text);
            this.message = String.format("%s (%s) does%s contain value : '%s'",element.getName(),element.getBy(), getConditional(), text);
        }
        validateCondition();
        return this;
    }


    public Verification visible()
    {
        this.condition = waitForElement();
        boolean displayed = this.element.isDisplayed();
        this.condition = this.condition && displayed;
        this.message = String.format("%s (%s) is%s visible ",element.getName(),element.getBy(), getConditional());
        validateCondition();
        return this;
    }

    public Verification present()
    {
        this.condition = waitForElement();
        this.message = String.format("%s (%s) is%s present",element.getName(),element.getBy(), getConditional());
        validateCondition();
        return this;
    }


    public class Not extends Verification{
        public Not(Element element, int timeout)
        {
            this.timeout = timeout;
            this.element = element;
        }

        @Override
        protected void validateCondition(){
            TestSuite.getTest().verifications.addVerification(this.message,!this.condition);
        }


    }
}
