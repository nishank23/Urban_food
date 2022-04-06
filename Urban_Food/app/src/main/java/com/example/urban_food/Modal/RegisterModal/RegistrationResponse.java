package com.example.urban_food.Modal.RegisterModal;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse{

	@SerializedName("phone")
	private String phone;

	@SerializedName("social_unique_id")
	private String socialUniqueId;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("login_by")
	private String loginBy;

	@SerializedName("email")
	private String email;

	public String getPhone(){
		return phone;
	}

	public String getSocialUniqueId(){
		return socialUniqueId;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getLoginBy(){
		return loginBy;
	}

	public String getEmail(){
		return email;
	}
}