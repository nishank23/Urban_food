package com.example.urban_food.Activites.SearchScreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.Search;

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
        ApiClient.getRetrofit().getSearch("1",search).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(@NonNull Call<Search> call, @NonNull Response<Search> response) {
                if (response.isSuccessful() && response.body() != null) {

                    view.onSuccessSearch(response.body());
                } else {
                    view.onError("Write Correct Name of Restaurant or food");

                }

            }

            @Override
            public void onFailure(@NonNull Call<Search> call, @NonNull Throwable t) {
                view.onError("Something went wrong");
            }
        });
    }

}
