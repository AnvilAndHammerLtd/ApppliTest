package com.kyriakosalexandrou.ampersandtest.models;

/**
 * Created by Kyriakos on 23/07/2016.
 */
public class SocialAggregates {
    private int mLikesCounter;
    private int mCommentsCounter;

    public SocialAggregates(int likesCounter, int commentsCounter) {
        this.mLikesCounter = likesCounter;
        this.mCommentsCounter = commentsCounter;
    }

    public int getLikesCounter() {
        return mLikesCounter;
    }

    public int getCommentsCounter() {
        return mCommentsCounter;
    }

    public void incrementLike() {
        mLikesCounter += 1;
    }

    public void DecreaseLike() {
        mLikesCounter = mLikesCounter > 0 ? mLikesCounter - 1 : 0;
    }
}
