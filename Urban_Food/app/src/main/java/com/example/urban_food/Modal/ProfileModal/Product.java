package com.example.urban_food.Modal.ProfileModal;

import java.util.List;

public class Product{
	private String outOfStock;
	private int featured;
	private List<ImagesItem> images;
	private int maxQuantity;
	private Shop shop;
	private List<Object> addons;
	private String description;
	private int avalability;
	private String foodType;
	private int shopId;
	private int addonStatus;
	private String name;
	private int id;
	private int position;
	private Prices prices;
	private String status;

	public String getOutOfStock(){
		return outOfStock;
	}

	public int getFeatured(){
		return featured;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public int getMaxQuantity(){
		return maxQuantity;
	}

	public Shop getShop(){
		return shop;
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