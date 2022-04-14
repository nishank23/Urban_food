package com.example.urban_food.Modal.CuisineModal;

import com.google.gson.annotations.SerializedName;

public class Cuisine{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}
}