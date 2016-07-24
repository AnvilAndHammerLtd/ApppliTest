package com.kyriakosalexandrou.ampersandtest.models;

import android.support.annotation.DrawableRes;

/**
 * Created by Kyriakos on 23/07/2016.
 */
public class NewsFeed {
    @DrawableRes
    private int mImageRes;
    private String mTitle;
    private boolean mIsFavourite;
    private SocialAggregates mSocialAggregates;

    public NewsFeed(int imageRes, String title, boolean favourite, SocialAggregates socialAggregates) {
        this.mImageRes = imageRes;
        this.mTitle = title;
        mIsFavourite = favourite;
        mSocialAggregates = socialAggregates;
    }

    public String getTitle() {
        return mTitle;
    }

    @DrawableRes
    public int getImageRes() {
        return mImageRes;
    }

    public SocialAggregates getAggregates() {
        return mSocialAggregates;
    }

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(boolean enabled) {
        mIsFavourite = enabled;
    }
}
