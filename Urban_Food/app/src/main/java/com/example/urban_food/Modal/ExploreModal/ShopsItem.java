package com.example.urban_food.Modal.ExploreModal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShopsItem{

	@SerializedName("country")
	private int country;

	@SerializedName("id_proof_photo")
	private Object idProofPhoto;

	@SerializedName("acc_num")
	private String accNum;

	@SerializedName("rating")
	private int rating;

	@SerializedName("food_licence_no")
	private String foodLicenceNo;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("open_close")
	private String openClose;

	@SerializedName("branch")
	private String branch;

	@SerializedName("gst_no")
	private Object gstNo;

	@SerializedName("estimated_delivery_time")
	private int estimatedDeliveryTime;

	@SerializedName("food_licence_no_photo")
	private String foodLicenceNoPhoto;

	@SerializedName("id")
	private int id;

	@SerializedName("default_banner")
	private String defaultBanner;

	@SerializedName("state")
	private Object state;

	@SerializedName("ifsc")
	private String ifsc;

	@SerializedName("maps_address")
	private String mapsAddress;

	@SerializedName("shopstatus")
	private String shopstatus;

	@SerializedName("longitude")
	private double longitude;

	@SerializedName("offer_percent")
	private int offerPercent;

	@SerializedName("device_id")
	private Object deviceId;

	@SerializedName("otp")
	private Object otp;

	@SerializedName("pan_card")
	private String panCard;

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

	@SerializedName("distance")
	private double distance;

	@SerializedName("city")
	private Object city;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("pan_card_photo")
	private String panCardPhoto;

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
	private String bankName;

	@SerializedName("timings")
	private List<TimingsItem> timings;

	@SerializedName("commission")
	private double commission;

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
	private String nameAsPerBank;

	@SerializedName("photo")
	private String photo;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("rating_status")
	private int ratingStatus;

	@SerializedName("device_token")
	private Object deviceToken;

	@SerializedName("passbook_photo")
	private String passbookPhoto;

	@SerializedName("shopopenstatus")
	private String shopopenstatus;

	public int getCountry(){
		return country;
	}

	public Object getIdProofPhoto(){
		return idProofPhoto;
	}

	public String getAccNum(){
		return accNum;
	}

	public int getRating(){
		return rating;
	}

	public String getFoodLicenceNo(){
		return foodLicenceNo;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public String getOpenClose(){
		return openClose;
	}

	public String getBranch(){
		return branch;
	}

	public Object getGstNo(){
		return gstNo;
	}

	public int getEstimatedDeliveryTime(){
		return estimatedDeliveryTime;
	}

	public String getFoodLicenceNoPhoto(){
		return foodLicenceNoPhoto;
	}

	public int getId(){
		return id;
	}

	public String getDefaultBanner(){
		return defaultBanner;
	}

	public Object getState(){
		return state;
	}

	public String getIfsc(){
		return ifsc;
	}

	public String getMapsAddress(){
		return mapsAddress;
	}

	public String getShopstatus(){
		return shopstatus;
	}

	public double getLongitude(){
		return longitude;
	}

	public int getOfferPercent(){
		return offerPercent;
	}

	public Object getDeviceId(){
		return deviceId;
	}

	public Object getOtp(){
		return otp;
	}

	public String getPanCard(){
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

	public double getDistance(){
		return distance;
	}

	public Object getCity(){
		return city;
	}

	public double getLatitude(){
		return latitude;
	}

	public String getPanCardPhoto(){
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

	public String getBankName(){
		return bankName;
	}

	public List<TimingsItem> getTimings(){
		return timings;
	}

	public double getCommission(){
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

	public String getNameAsPerBank(){
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

	public Object getDeviceToken(){
		return deviceToken;
	}

	public String getPassbookPhoto(){
		return passbookPhoto;
	}

	public String getShopopenstatus(){
		return shopopenstatus;
	}
}