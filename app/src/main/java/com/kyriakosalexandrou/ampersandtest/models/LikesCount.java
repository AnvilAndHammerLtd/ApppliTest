package com.kyriakosalexandrou.ampersandtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyriakos on 26/07/2016.
 */
public class LikesCount {

    @SerializedName("image_id")
    @Expose
    private Integer imageId;
    @SerializedName("likes")
    @Expose
    private Integer likes;

    public LikesCount(Integer imageId, Integer likes) {
        this.imageId = imageId;
        this.likes = likes;
    }

    /**
     * @return The imageId
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * @param imageId The image_id
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * @return The likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes The likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

}