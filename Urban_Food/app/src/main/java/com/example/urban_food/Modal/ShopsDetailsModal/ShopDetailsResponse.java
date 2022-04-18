package com.example.urban_food.Modal.ShopsDetailsModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShopDetailsResponse{

	@SerializedName("featured_products")
	private List<FeaturedProductsItem> featuredProducts;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public List<FeaturedProductsItem> getFeaturedProducts(){
		return featuredProducts;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}