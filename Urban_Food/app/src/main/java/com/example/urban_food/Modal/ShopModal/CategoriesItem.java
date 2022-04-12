package com.example.urban_food.Modal.ShopModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoriesItem{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("parent_id")
	private int parentId;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("position")
	private Object position;

	@SerializedName("status")
	private String status;

	@SerializedName("products")
	private List<ProductsItem> products;

	public int getShopId(){
		return shopId;
	}

	public int getParentId(){
		return parentId;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public Object getPosition(){
		return position;
	}

	public String getStatus(){
		return status;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}