package com.example.urban_food.model;

import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("product_image")
	private ProductImage productImage;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("product_price")
	private ProductPrice productPrice;

	@SerializedName("shop_name")
	private String shopName;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("shop_open_close")
	private String shopOpenClose;

	public void setShopId(int shopId){
		this.shopId = shopId;
	}

	public int getShopId(){
		return shopId;
	}

	public void setProductImage(ProductImage productImage){
		this.productImage = productImage;
	}

	public ProductImage getProductImage(){
		return productImage;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductPrice(ProductPrice productPrice){
		this.productPrice = productPrice;
	}

	public ProductPrice getProductPrice(){
		return productPrice;
	}

	public void setShopName(String shopName){
		this.shopName = shopName;
	}

	public String getShopName(){
		return shopName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public String getShopOpenClose() {
		return shopOpenClose;
	}

	public void setShopOpenClose(String shopOpenClose) {
		this.shopOpenClose = shopOpenClose;
	}

	@Override
 	public String toString(){
		return 
			"ProductsItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",product_image = '" + productImage + '\'' + 
			",product_id = '" + productId + '\'' + 
			",product_price = '" + productPrice + '\'' + 
			",shop_name = '" + shopName + '\'' + 
			",product_name = '" + productName + '\'' + 
			",shop_open_close = '" + shopOpenClose + '\'' +
			"}";
		}
}