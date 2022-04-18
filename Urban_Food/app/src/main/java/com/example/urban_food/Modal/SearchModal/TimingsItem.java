package com.example.urban_food.Modal.SearchModal;

import com.google.gson.annotations.SerializedName;

public class TimingsItem{

	@SerializedName("shop_id")
	private int shopId;

	@SerializedName("start_time")
	private String startTime;

	@SerializedName("end_time")
	private String endTime;

	@SerializedName("id")
	private int id;

	@SerializedName("day")
	private String day;

	public int getShopId(){
		return shopId;
	}

	public String getStartTime(){
		return startTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public int getId(){
		return id;
	}

	public String getDay(){
		return day;
	}
}