package com.example.urban_food.Modal.SearchModal;

import com.google.gson.annotations.SerializedName;

public class Pivot{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("cuisine_id")
	private int cuisineId;

	public int getShopId(){
		return shopId;
	}

	public int getCuisineId(){
		return cuisineId;
	}
}