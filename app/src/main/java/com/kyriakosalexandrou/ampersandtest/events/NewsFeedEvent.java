package com.kyriakosalexandrou.ampersandtest.events;

import com.kyriakosalexandrou.ampersandtest.models.NewsFeed;

public class NewsFeedEvent {
    private ErrorEvent mErrorEvent;
    private NewsFeed mNewsFeed;

    public NewsFeedEvent(ErrorEvent errorEvent) {
        this.mErrorEvent = errorEvent;
    }

    public NewsFeed getNewsFeed() {
        return mNewsFeed;
    }

    public ErrorEvent getErrorEvent() {
        return mErrorEvent;
    }

    public void setNewsFeed(NewsFeed newsFeed) {
        mNewsFeed = newsFeed;
    }
}
