
package com.kyriakosalexandrou.ampersandtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsCount {

    @SerializedName("image_id")
    @Expose
    private Integer imageId;
    @SerializedName("comments")
    @Expose
    private Integer comments;

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
     * @return The comments
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

}
