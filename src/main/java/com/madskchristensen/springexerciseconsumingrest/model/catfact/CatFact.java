package com.madskchristensen.springexerciseconsumingrest.model.catfact;

public class CatFact {
    private String text;
    private Status status;

    public CatFact() {
        status = new Status();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "text='" + text + '\'' +
                ", status=" + status +
                '}';
    }
}