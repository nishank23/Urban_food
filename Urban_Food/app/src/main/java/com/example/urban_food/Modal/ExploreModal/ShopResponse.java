package com.example.urban_food.Modal.ExploreModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShopResponse{

	@SerializedName("current_order")
	private Object currentOrder;

	@SerializedName("shops")
	private List<ShopsItem> shops;

	@SerializedName("currency")
	private String currency;

	@SerializedName("banners")
	private List<Object> banners;

	public Object getCurrentOrder(){
		return currentOrder;
	}

	public List<ShopsItem> getShops(){
		return shops;
	}

	public String getCurrency(){
		return currency;
	}

	public List<Object> getBanners(){
		return banners;
	}
}