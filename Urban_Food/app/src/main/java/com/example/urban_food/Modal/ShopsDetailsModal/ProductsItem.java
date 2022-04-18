package com.example.urban_food.Modal.ShopsDetailsModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("out_of_stock")
	private String outOfStock;

	@SerializedName("featured")
	private int featured;

	@SerializedName("images")
	private List<Object> images;

	@SerializedName("max_quantity")
	private int maxQuantity;

	@SerializedName("addons")
	private List<Object> addons;

	@SerializedName("description")
	private String description;

	@SerializedName("avalability")
	private int avalability;

	@SerializedName("food_type")
	private String foodType;

	@SerializedName("cart")
	private List<Object> cart;

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("addon_status")
	private int addonStatus;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("position")
	private int position;

	@SerializedName("prices")
	private Prices prices;

	@SerializedName("status")
	private String status;

	public String getOutOfStock(){
		return outOfStock;
	}

	public int getFeatured(){
		return featured;
	}

	public List<Object> getImages(){
		return images;
	}

	public int getMaxQuantity(){
		return maxQuantity;
	}

	public List<Object> getAddons(){
		return addons;
	}

	public String getDescription(){
		return description;
	}

	public int getAvalability(){
		return avalability;
	}

	public String getFoodType(){
		return foodType;
	}

	public List<Object> getCart(){
		return cart;
	}

	public int getShopId(){
		return shopId;
	}

	public int getAddonStatus(){
		return addonStatus;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getPosition(){
		return position;
	}

	public Prices getPrices(){
		return prices;
	}

	public String getStatus(){
		return status;
	}
}