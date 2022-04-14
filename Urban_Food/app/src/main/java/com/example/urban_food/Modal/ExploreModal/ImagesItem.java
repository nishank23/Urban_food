package com.example.urban_food.Modal.ExploreModal;

import com.google.gson.annotations.SerializedName;

public class ImagesItem{

	@SerializedName("position")
	private int position;

	@SerializedName("url")
	private String url;

	public int getPosition(){
		return position;
	}

	public String getUrl(){
		return url;
	}
}