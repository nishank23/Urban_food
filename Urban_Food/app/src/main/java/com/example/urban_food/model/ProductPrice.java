package com.example.urban_food.model;

import com.google.gson.annotations.SerializedName;

public class ProductPrice{

	@SerializedName("price")
	private int price;

	@SerializedName("discount")
	private int discount;

	@SerializedName("currency")
	private String currency;

	@SerializedName("discount_type")
	private String discountType;

	@SerializedName("orignal_price")
	private int orignalPrice;

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setDiscountType(String discountType){
		this.discountType = discountType;
	}

	public String getDiscountType(){
		return discountType;
	}

	public void setOrignalPrice(int orignalPrice){
		this.orignalPrice = orignalPrice;
	}

	public int getOrignalPrice(){
		return orignalPrice;
	}

	@Override
 	public String toString(){
		return 
			"ProductPrice{" + 
			"price = '" + price + '\'' + 
			",discount = '" + discount + '\'' + 
			",currency = '" + currency + '\'' + 
			",discount_type = '" + discountType + '\'' + 
			",orignal_price = '" + orignalPrice + '\'' + 
			"}";
		}
}