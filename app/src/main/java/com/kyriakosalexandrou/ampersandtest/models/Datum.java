
package com.kyriakosalexandrou.ampersandtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("event_id")
    @Expose
    private Integer eventId;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("userLiked")
    @Expose
    private Boolean userLiked;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("likes_count")
    @Expose
    private List<LikesCount> likesCount = new ArrayList<>();
    @SerializedName("comments_count")
    @Expose
    private List<CommentsCount> commentsCount = new ArrayList<>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The comment
     */
    public Object getComment() {
        return comment;
    }

    /**
     * @param comment The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The eventId
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * @param eventId The event_id
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * @return The active
     */
    public Object getActive() {
        return active;
    }

    /**
     * @param active The active
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The userLiked
     */
    public Boolean getUserLiked() {
        return userLiked;
    }

    /**
     * @param userLiked The userLiked
     */
    public void setUserLiked(Boolean userLiked) {
        this.userLiked = userLiked;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The likesCount
     */
    public List<LikesCount> getLikesCount() {
        return likesCount;
    }

    /**
     * @param likesCount The likes_count
     */
    public void setLikesCount(List<LikesCount> likesCount) {
        this.likesCount = likesCount;
    }

    /**
     * @return The commentsCount
     */
    public List<CommentsCount> getCommentsCount() {
        return commentsCount;
    }

    /**
     * @param commentsCount The comments_count
     */
    public void setCommentsCount(List<CommentsCount> commentsCount) {
        this.commentsCount = commentsCount;
    }

}
