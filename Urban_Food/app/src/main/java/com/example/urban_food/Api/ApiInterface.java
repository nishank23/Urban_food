package com.example.urban_food.Api;

import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.AddFavoriteResponse;
import com.example.urban_food.model.Address;
import com.example.urban_food.model.ChangePassword;
import com.example.urban_food.model.ClearCart;
import com.example.urban_food.model.ClearCart;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.FavoriteList;
import com.example.urban_food.model.ForgotPassword;
import com.example.urban_food.model.LoginModel;
import com.example.urban_food.model.Order;
import com.example.urban_food.model.Otp;
import com.example.urban_food.model.RegisterModel;
import com.example.urban_food.model.ResetPassword;
import com.example.urban_food.model.RestaurantsData;
import com.example.urban_food.model.Search;
import com.example.urban_food.model.ShopDetail;
import com.example.urban_food.model.User;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @GET("api/user/shops/most_popular")
    Call<RestaurantsData> getPopularShops(@QueryMap HashMap<String, String> map);

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

    @FormUrlEncoded
    @POST("api/user/favorite")
    Call<AddFavoriteResponse> addFavorite(@Field("shop_id")String shop_id);


    @GET("api/user/favorite")
    Call<FavoriteList> getFavorite();


    @GET("api/user/clear/cart")
    Call<ClearCart> clearCartCall();


    @FormUrlEncoded
    @POST("api/user/profile/password")
    Call<ChangePassword> changePassword(@FieldMap HashMap<String, String> map);


    @FormUrlEncoded
    @POST("api/user/forgot/password")
    Call<ForgotPassword> forgotPassword(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("api/user/reset/password")
    Call<ResetPassword> resetPassword(@FieldMap HashMap<String, String> map);

    @DELETE("api/user/favorite/{id}")
    Call<AddFavoriteResponse> deleteFavorite(@Path("id") String shopid);


    @POST("api/user/profile")
    Call<User> updateProfile(@Body RequestBody requestBody);

    @GET("json?")
    Call<ResponseBody> getResponse(@Query("latlng") String param1, @Query("key") String param2);



    @POST("api/user/address")
    Call<Address> addAddress(@Body Address address);


    @PATCH("api/user/address/{id}")
    Call<Address> updateAddress(@Path("id") int id,@Body Address address);


    @DELETE("api/user/address/{id}")
    Call<AddFavoriteResponse> deleteAddress(@Path("id") String addressid);

    @GET("api/user/address")
    Call<List<Address>> getAddress();


    @GET("api/user/order")
    Call<List<Order>> getOrder();


    @GET("api/user/order/{id}")
    Call<Order> getOrderbyid(@Path("id") int id);



    @GET("api/user/ongoing/order")
    Call<List<Order>> getOngoingOrder();

    @FormUrlEncoded
    @POST("api/user/order")
    Call<Order> orderPlaced(@FieldMap HashMap<String, String> map);


    @DELETE("api/user/order/{id}")
    Call<String> cancelOngoingOrder(@Path("id") String orderid,@Query("reason") String reason);

    @FormUrlEncoded
    @POST("api/user/reorder")
    Call<AddCart> reorder(@Field("order_id") String id);

}



