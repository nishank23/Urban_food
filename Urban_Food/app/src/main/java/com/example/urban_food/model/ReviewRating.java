package com.example.urban_food.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewRating {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_rating")
    @Expose
    private Integer userRating;
    @SerializedName("user_comment")
    @Expose
    private String userComment;
    @SerializedName("transporter_id")
    @Expose
    private Integer transporterId;
    @SerializedName("transporter_rating")
    @Expose
    private Integer transporterRating;
    @SerializedName("transporter_comment")
    @Expose
    private String transporterComment;
    @SerializedName("shop_id")
    @Expose
    private Integer shopId;
    @SerializedName("shop_rating")
    @Expose
    private Integer shopRating;
    @SerializedName("shop_comment")
    @Expose
    private String shopComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public Integer getTransporterRating() {
        return transporterRating;
    }

    public void setTransporterRating(Integer transporterRating) {
        this.transporterRating = transporterRating;
    }

    public String getTransporterComment() {
        return transporterComment;
    }

    public void setTransporterComment(String transporterComment) {
        this.transporterComment = transporterComment;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getShopRating() {
        return shopRating;
    }

    public void setShopRating(Integer shopRating) {
        this.shopRating = shopRating;
    }

    public String getShopComment() {
        return shopComment;
    }

    public void setShopComment(String shopComment) {
        this.shopComment = shopComment;
    }
}
