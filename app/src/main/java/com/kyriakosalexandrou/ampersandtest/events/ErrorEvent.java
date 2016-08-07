package com.kyriakosalexandrou.ampersandtest.events;

public class ErrorEvent {
    private String mErrorMessage;

    public ErrorEvent(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}