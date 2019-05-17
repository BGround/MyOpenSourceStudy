package com.example.eventbusdemo;

public class MessageEvent {
    private String Message;

    public MessageEvent(String message){
        this.Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
