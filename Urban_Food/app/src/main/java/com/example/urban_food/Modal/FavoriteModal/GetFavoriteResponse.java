package com.example.urban_food.Modal.FavoriteModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetFavoriteResponse{

	@SerializedName("un_available")
	private List<Object> unAvailable;

	@SerializedName("available")
	private List<AvailableItem> available;

	public List<Object> getUnAvailable(){
		return unAvailable;
	}

	public List<AvailableItem> getAvailable(){
		return available;
	}
}