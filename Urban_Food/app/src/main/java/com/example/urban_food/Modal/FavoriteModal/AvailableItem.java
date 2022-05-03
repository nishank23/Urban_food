package com.example.urban_food.Modal.FavoriteModal;

import com.example.urban_food.model.Shop;
import com.google.gson.annotations.SerializedName;

public class AvailableItem{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("shop")
	private Shop shop;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	public int getShopId(){
		return shopId;
	}

	public Shop getShop(){
		return shop;
	}

	public int getUserId(){
		return userId;
	}

	public int getId(){
		return id;
	}
}