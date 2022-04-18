package com.example.urban_food.Modal.SearchModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShopsItem{

	@SerializedName("country")
	private int country;

	@SerializedName("id_proof_photo")
	private Object idProofPhoto;

	@SerializedName("acc_num")
	private Object accNum;

	@SerializedName("rating")
	private int rating;

	@SerializedName("food_licence_no")
	private Object foodLicenceNo;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("open_close")
	private String openClose;

	@SerializedName("branch")
	private Object branch;

	@SerializedName("gst_no")
	private Object gstNo;

	@SerializedName("estimated_delivery_time")
	private int estimatedDeliveryTime;

	@SerializedName("food_licence_no_photo")
	private Object foodLicenceNoPhoto;

	@SerializedName("id")
	private int id;

	@SerializedName("default_banner")
	private String defaultBanner;

	@SerializedName("state")
	private int state;

	@SerializedName("ifsc")
	private Object ifsc;

	@SerializedName("maps_address")
	private String mapsAddress;

	@SerializedName("longitude")
	private double longitude;

	@SerializedName("offer_percent")
	private int offerPercent;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("otp")
	private int otp;

	@SerializedName("pan_card")
	private Object panCard;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("cuisines")
	private List<CuisinesItem> cuisines;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("identity_proof")
	private Object identityProof;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("favorite")
	private Object favorite;

	@SerializedName("status")
	private String status;

	@SerializedName("city")
	private int city;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("pan_card_photo")
	private Object panCardPhoto;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("pure_veg")
	private int pureVeg;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("gst_no_file")
	private Object gstNoFile;

	@SerializedName("ratings")
	private Object ratings;

	@SerializedName("bank_name")
	private Object bankName;

	@SerializedName("timings")
	private List<TimingsItem> timings;

	@SerializedName("commission")
	private int commission;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("shop_unique_id")
	private String shopUniqueId;

	@SerializedName("popular")
	private int popular;

	@SerializedName("email")
	private String email;

	@SerializedName("offer_min_amount")
	private int offerMinAmount;

	@SerializedName("address")
	private String address;

	@SerializedName("name_as_per_bank")
	private Object nameAsPerBank;

	@SerializedName("photo")
	private String photo;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("rating_status")
	private int ratingStatus;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("passbook_photo")
	private Object passbookPhoto;

	public int getCountry(){
		return country;
	}

	public Object getIdProofPhoto(){
		return idProofPhoto;
	}

	public Object getAccNum(){
		return accNum;
	}

	public int getRating(){
		return rating;
	}

	public Object getFoodLicenceNo(){
		return foodLicenceNo;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public String getOpenClose(){
		return openClose;
	}

	public Object getBranch(){
		return branch;
	}

	public Object getGstNo(){
		return gstNo;
	}

	public int getEstimatedDeliveryTime(){
		return estimatedDeliveryTime;
	}

	public Object getFoodLicenceNoPhoto(){
		return foodLicenceNoPhoto;
	}

	public int getId(){
		return id;
	}

	public String getDefaultBanner(){
		return defaultBanner;
	}

	public int getState(){
		return state;
	}

	public Object getIfsc(){
		return ifsc;
	}

	public String getMapsAddress(){
		return mapsAddress;
	}

	public double getLongitude(){
		return longitude;
	}

	public int getOfferPercent(){
		return offerPercent;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public int getOtp(){
		return otp;
	}

	public Object getPanCard(){
		return panCard;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public List<CuisinesItem> getCuisines(){
		return cuisines;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public Object getIdentityProof(){
		return identityProof;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public Object getFavorite(){
		return favorite;
	}

	public String getStatus(){
		return status;
	}

	public int getCity(){
		return city;
	}

	public double getLatitude(){
		return latitude;
	}

	public Object getPanCardPhoto(){
		return panCardPhoto;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getPureVeg(){
		return pureVeg;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getGstNoFile(){
		return gstNoFile;
	}

	public Object getRatings(){
		return ratings;
	}

	public Object getBankName(){
		return bankName;
	}

	public List<TimingsItem> getTimings(){
		return timings;
	}

	public int getCommission(){
		return commission;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public String getShopUniqueId(){
		return shopUniqueId;
	}

	public int getPopular(){
		return popular;
	}

	public String getEmail(){
		return email;
	}

	public int getOfferMinAmount(){
		return offerMinAmount;
	}

	public String getAddress(){
		return address;
	}

	public Object getNameAsPerBank(){
		return nameAsPerBank;
	}

	public String getPhoto(){
		return photo;
	}

	public String getAvatar(){
		return avatar;
	}

	public int getRatingStatus(){
		return ratingStatus;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public Object getPassbookPhoto(){
		return passbookPhoto;
	}
}