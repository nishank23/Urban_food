package com.example.urban_food.Modal.ProfileModal;

public class AddressesItem{
	private String country;
	private String pincode;
	private String city;
	private double latitude;
	private String type;
	private String building;
	private int userId;
	private Object street;
	private String map_address;
	private int id;
	private String state;
	private String landmark;
	private double longitude;

	public String getCountry(){
		return country;
	}

	public String getPincode(){
		return pincode;
	}

	public String getCity(){
		return city;
	}

	public double getLatitude(){
		return latitude;
	}

	public String getType(){
		return type;
	}

	public String getBuilding(){
		return building;
	}

	public int getUserId(){
		return userId;
	}

	public Object getStreet(){
		return street;
	}

	public String getMapAddress(){
		return map_address;
	}

	public int getId(){
		return id;
	}

	public String getState(){
		return state;
	}

	public String getLandmark(){
		return landmark;
	}

	public double getLongitude(){
		return longitude;
	}
}
