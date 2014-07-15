package com.prototest.appdriver;

public class Verification {
    public Not Not;
    protected Element element;
    protected boolean isTrue;
    protected boolean condition;
    protected String message;
    protected String conditional;
    protected int timeout;

    public Verification(){}

    public Verification(Element element, int timeout){
        this.element = element;
        this.Not = new Not(element);
        this.conditional = " not";
        this.timeout=timeout;
    }

    protected void validateCondition(){
        if(!this.condition)
        {
            System.out.println("Verification Failed : " + this.message);
        }

    }

    protected boolean waitForElement(){
        for(int i=0;i<this.timeout;i++){
            if(element.isPresent())
                return true;
            else
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
        }
        this.condition=false;
        this.message = String.format("Element (%s) is%s present",element.getBy().toString(), conditional);
        return false;
    }

    public Verification containsText(String text)
    {
        if(waitForElement()){
            this.condition = this.element.getText().contains(text);
            this.message = String.format("Element (%s) does%s contain text %s",element.getBy().toString(), conditional, text);
        }

        validateCondition();
        return this;
    }

    public Verification visible()
    {
        if(waitForElement()){
            this.condition = this.element.isDisplayed();
            this.message = String.format("Element (%s) is%s visible ",element.getBy().toString(), conditional);
        }
        validateCondition();
        return this;
    }

    public Verification present()
    {
        if(waitForElement()){
            this.condition = this.element.isPresent();
            this.message = String.format("Element (%s) is%s present",element.getBy().toString(), conditional);
        }
        validateCondition();
        return this;
    }


    public class Not extends Verification{
        public Not(Element element)
        {

            this.element = element;
            this.conditional = "";
        }
        @Override
        public void validateCondition(){
            if(this.condition)
            {
                System.out.println("Verification Failed : " + this.message);
            }
        }

    }
}
