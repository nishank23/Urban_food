package com.example.urban_food.Activites.SearchScreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.LoginModal.LoginResponse;
import com.example.urban_food.Modal.ProfileModal.ProfileResponse;
import com.example.urban_food.Modal.SearchModal.SearchResponse;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityPresenter {

    SearchActivityView view;

    public SearchActivityPresenter(SearchActivityView view) {
        this.view = view;
    }

    public void getSearch(String search) {
        ApiClient.getRetrofit().getSearch("1",search).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    view.onSuccessSearch(response.body());
                } else {
                    view.onError("Write Correct Name of Restaurant or food");

                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                view.onError("Something went wrong");
            }
        });
    }

}
