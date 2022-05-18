package com.example.urban_food.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddCart {

    @SerializedName("delivery_charges")
    @Expose
    private Integer deliveryCharges;
    @SerializedName("delivery_free_minimum")
    @Expose
    private Integer deliveryFreeMinimum;
    @SerializedName("tax_percentage")
    @Expose
    private Integer taxPercentage;
    @SerializedName("grabit_comission")
    @Expose
    private Integer grabitComission;
    @SerializedName("grabit_comission_tax")
    @Expose
    private Integer grabitComissionTax;

    public List<Cart> getProducts() {
        return products;
    }

    public void setProducts(List<Cart> products) {
        this.products = products;
    }

    @SerializedName("carts")
    @Expose
    private List<Cart> products = new ArrayList<>();
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order_amount")
    @Expose
    private Integer orderAmount;

    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }





    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }




    public Integer getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Integer deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Integer getDeliveryFreeMinimum() {
        return deliveryFreeMinimum;
    }

    public void setDeliveryFreeMinimum(Integer deliveryFreeMinimum) {
        this.deliveryFreeMinimum = deliveryFreeMinimum;
    }

    public Integer getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Integer taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public List<Cart> getProductList() {
        return products;
    }

    public void setProductList(List<Cart> products) {
        this.products = products;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getGrabitComission() {
        return grabitComission;
    }

    public void setGrabitComission(Integer grabitComission) {
        this.grabitComission = grabitComission;
    }

    public Integer getGrabitComissionTax() {
        return grabitComissionTax;
    }

    public void setGrabitComissionTax(Integer grabitComissionTax) {
        this.grabitComissionTax = grabitComissionTax;
    }
}
