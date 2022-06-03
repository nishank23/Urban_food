package com.example.urban_food.Activities.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityClickCuisineBinding;
import com.example.urban_food.fragment.explore.ExploreInterface;
import com.example.urban_food.fragment.explore.ExplorePresenter;
import com.example.urban_food.fragment.explore.ExploreView;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.RestaurantsData;
import com.example.urban_food.model.Shop;

import java.util.HashMap;
import java.util.List;

public class ClickCuisineActivity extends AppCompatActivity implements ExploreView {
    ActivityClickCuisineBinding binding;
    String cuisine_id = "";
    ExplorePresenter explorePresenter = new ExplorePresenter(this);
    PopularthisWeekAdapter adapterCuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClickCuisineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cuisine_id = getIntent().getStringExtra("cuisine");
        binding.ivBackCuisines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (Common.isConnected()) {
            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvCuisinesShops.setVisibility(View.GONE);

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("user_id", String.valueOf(GlobalData.users.getId()));
            map.put("latitude", String.valueOf(GlobalData.latitude));
            map.put("longitude", String.valueOf(GlobalData.longitude));
            map.put("cuisine[" + "" + 0 + "]", cuisine_id);
            explorePresenter.shops(map);
        } else {
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);
            binding.rvCuisinesShops.setVisibility(View.GONE);
        }


    }

    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {

    }

    @Override
    public void onSuccessShops(RestaurantsData shopsItemList) {
        if (shopsItemList.getShops().isEmpty()) {
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvCuisinesShops.setVisibility(View.GONE);
        } else {
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvCuisinesShops.setVisibility(View.VISIBLE);

            PopularthisWeekAdapter adapterCuisine = new PopularthisWeekAdapter(this, shopsItemList.getShops(), new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {
                    Intent intent = new Intent(ClickCuisineActivity.this,ShopsDetailsActivity.class);
                    intent.putExtra("ShopId",data);
                    intent.putExtra("pathImage",path);
                    startActivity(intent);
                }
            });
            binding.rvCuisinesShops.setAdapter(adapterCuisine);
            binding.rvCuisinesShops.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    public void onSuccessShopsPopular(List<Shop> shopsItemList) {

    }

    @Override
    public void onErrorShops() {
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutNodata.clNoData.setVisibility(View.GONE);
        binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
        binding.rvCuisinesShops.setVisibility(View.GONE);
    }

    @Override
    public void showProgressShops() {

    }

    @Override
    public void dismissProgressShops() {

    }
}