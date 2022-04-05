package com.example.urban_food.Modal.OtpModal;

import com.google.gson.annotations.SerializedName;

public class OtpResponse{

	@SerializedName("otp")
	private int otp;

	@SerializedName("message")
	private String message;

	public int getOtp(){
		return otp;
	}

	public String getMessage(){
		return message;
	}
}