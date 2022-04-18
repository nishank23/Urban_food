package com.example.urban_food.Modal.SearchModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResponse{

	@SerializedName("shops")
	private List<ShopsItem> shops;

	@SerializedName("products")
	private List<ProductsItem> products;

	public List<ShopsItem> getShops(){
		return shops;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}