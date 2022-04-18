package com.example.urban_food.Modal.SearchModal;

import com.google.gson.annotations.SerializedName;

public class Ratings{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("rating")
	private String rating;

	public int getShopId(){
		return shopId;
	}

	public String getRating(){
		return rating;
	}
}