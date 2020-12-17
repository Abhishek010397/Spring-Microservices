package com.labcerebrone.RestfulWebServices.bean;

public class HelloWorldBean {

    private String message;
    public HelloWorldBean(String message){
        this.message = message;
    }

    //always create Getter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", "message");
        //ouput {"message":"Hello-World"}
    }
}
