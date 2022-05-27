package com.example.urban_food.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shop {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("offer_min_amount")
    @Expose
    private Double offerMinAmount;
    @SerializedName("offer_percent")
    @Expose
    private Integer offerPercent;
    @SerializedName("estimated_delivery_time")
    @Expose
    private Integer estimatedDeliveryTime;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("maps_address")
    @Expose
    private String mapsAddress;
    @SerializedName("open_close")
    @Expose
    private String openClose;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("pure_veg")
    @Expose
    private Integer pureVeg;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("rating_status")
    @Expose
    private Integer ratingStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("shopstatus")
    @Expose
    private String shopstatus;
    @SerializedName("cuisines")
    @Expose
    private List<Cuisine> cuisines = null;
    @SerializedName("timings")
    @Expose
    private List<Timing> timings = null;
    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    @SerializedName("favorite")
    @Expose
    private Favorite favorite;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("current_order")
    @Expose
    private Order currentOrder;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOfferMinAmount() {
        return offerMinAmount;
    }

    public void setOfferMinAmount(Double offerMinAmount) {
        this.offerMinAmount = offerMinAmount;
    }

    public Integer getOfferPercent() {
        return offerPercent;
    }

    public void setOfferPercent(Integer offerPercent) {
        this.offerPercent = offerPercent;
    }

    public Integer getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapsAddress() {
        return mapsAddress;
    }

    public void setMapsAddress(String mapsAddress) {
        this.mapsAddress = mapsAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPureVeg() {
        return pureVeg;
    }

    public void setPureVeg(Integer pureVeg) {
        this.pureVeg = pureVeg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Timing> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing> timings) {
        this.timings = timings;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingStatus() {
        return ratingStatus;
    }

    public void setRatingStatus(Integer ratingStatus) {
        this.ratingStatus = ratingStatus;
    }

    public String getShopstatus() {
        return shopstatus;
    }

    public void setShopstatus(String shopstatus) {
        this.shopstatus = shopstatus;
    }

    public String getOpenClose() {
        return openClose;
    }

    public void setOpenClose(String openClose) {
        this.openClose = openClose;
    }
    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
