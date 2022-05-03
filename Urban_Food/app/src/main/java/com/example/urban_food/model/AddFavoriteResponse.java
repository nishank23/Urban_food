package com.example.urban_food.model;

import com.google.gson.annotations.SerializedName;

public class AddFavoriteResponse{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}