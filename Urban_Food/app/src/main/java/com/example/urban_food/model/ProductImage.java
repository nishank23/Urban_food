package com.example.urban_food.model;

import com.google.gson.annotations.SerializedName;

public class ProductImage{

	@SerializedName("url")
	private String url;

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"ProductImage{" + 
			"url = '" + url + '\'' + 
			"}";
		}
}