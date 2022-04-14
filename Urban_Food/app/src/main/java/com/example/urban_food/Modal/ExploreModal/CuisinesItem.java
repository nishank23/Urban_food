package com.example.urban_food.Modal.ExploreModal;

import com.google.gson.annotations.SerializedName;

public class CuisinesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("pivot")
	private Pivot pivot;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public Pivot getPivot(){
		return pivot;
	}

	public int getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}
}