package com.madskchristensen.springexerciseconsumingrest.model.catfact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
    private boolean verified;

    public Status() {
        this.verified = false;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Status{" +
                "verified=" + verified +
                '}';
    }
}
