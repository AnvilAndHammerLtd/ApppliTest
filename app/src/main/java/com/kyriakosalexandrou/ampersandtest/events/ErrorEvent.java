package com.kyriakosalexandrou.ampersandtest.events;

/**
 * Created by Kyriakos on 25/06/2016.
 */
public class ErrorEvent {
    private String mErrorMessage;

    public ErrorEvent(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}