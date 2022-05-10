package com.example.urban_food.Api;

import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.AddFavoriteResponse;
import com.example.urban_food.model.ClearCart;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.FavoriteList;
import com.example.urban_food.model.LoginModel;
import com.example.urban_food.model.Otp;
import com.example.urban_food.model.RegisterModel;
import com.example.urban_food.model.RestaurantsData;
import com.example.urban_food.model.Search;
import com.example.urban_food.model.ShopDetail;
import com.example.urban_food.model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
/*
    @GET("Common/get_property_masters")*//*
    Call<PropertmasterResponse> getPropertyMaster(@Query("user_id")String user_id);*//*

    @GET("Common/get_states")*//*
    Call<StateResponse> getState();
*//*
    @FormUrlEncoded
    @POST("Common/get_cities")
  *//*  Call<CityResponse> getCity(@Field("state_id")String state_id);
*/
    @FormUrlEncoded
    @POST("api/user/otp")
    Call<Otp> getingOtp(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("api/user/register")
    Call<RegisterModel> registration(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("oauth/token")
    Call<LoginModel> Login(@FieldMap HashMap<String,String> map);

    @GET("api/user/profile")
    Call<User> getProfile(@QueryMap HashMap<String, String> map);


    @GET("api/user/shops")
    Call<RestaurantsData> getShops(@QueryMap HashMap<String, String> map);

    @GET("api/user/cuisines")
    Call<List<Cuisine>> getCuisineCategories();

    @GET("api/user/search")
    Call<Search> getSearch(@Query("user_id")String user_id, @Query("name")String name);

    @GET("api/user/categories")
    Call<ShopDetail> getShopDetails(@QueryMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("api/user/cart")
    Call<AddCart> postCartDetails(@FieldMap HashMap<String,String> map);

    @GET("api/user/cart")
    Call<AddCart> getCartDetail();

    Call<ClearCart> clearCartCall();

    Call<AddFavoriteResponse> addFavorite(String shopid);

    Call<FavoriteList> getFavorite();
}