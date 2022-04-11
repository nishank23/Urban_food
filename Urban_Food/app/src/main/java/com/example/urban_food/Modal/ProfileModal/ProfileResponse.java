package com.example.urban_food.Modal.ProfileModal;

import java.util.List;

public class ProfileResponse{
	private Object stripeCustId;
	private List<String> paymentMode;
	private List<AddressesItem> addresses;
	private String deviceId;
	private int walletBalance;
	private String deviceType;
	private String otp;
	private String avatar;
	private String type;
	private List<CartItem> cart;
	private String phone;
	private String socialUniqueId;
	private String deviceToken;
	private String name;
	private String currency;
	private int id;
	private String loginBy;
	private String email;
	private Object braintreeId;
	private String status;

	public Object getStripeCustId(){
		return stripeCustId;
	}

	public List<String> getPaymentMode(){
		return paymentMode;
	}

	public List<AddressesItem> getAddresses(){
		return addresses;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public int getWalletBalance(){
		return walletBalance;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public String getOtp(){
		return otp;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getType(){
		return type;
	}

	public List<CartItem> getCart(){
		return cart;
	}

	public String getPhone(){
		return phone;
	}

	public String getSocialUniqueId(){
		return socialUniqueId;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public String getName(){
		return name;
	}

	public String getCurrency(){
		return currency;
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

	public Object getBraintreeId(){
		return braintreeId;
	}

	public String getStatus(){
		return status;
	}
}