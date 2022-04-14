package com.example.urban_food.Modal.ExploreModal;

import com.google.gson.annotations.SerializedName;

public class Prices{

	@SerializedName("price")
	private int price;

	@SerializedName("discount")
	private int discount;

	@SerializedName("currency")
	private String currency;

	@SerializedName("id")
	private int id;

	@SerializedName("discount_type")
	private String discountType;

	@SerializedName("orignal_price")
	private int orignalPrice;

	public int getPrice(){
		return price;
	}

	public int getDiscount(){
		return discount;
	}

	public String getCurrency(){
		return currency;
	}

	public int getId(){
		return id;
	}

	public String getDiscountType(){
		return discountType;
	}

	public int getOrignalPrice(){
		return orignalPrice;
	}
}