package com.example.urban_food.fragment.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activities.SearchScreen.SearchActivity;
import com.example.urban_food.Activities.ShopsDetail.ClickCuisineActivity;
import com.example.urban_food.Activities.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.Adapter.CuisineCategoryAdapter;
import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.FragmentExploreBinding;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.Shop;

import java.util.HashMap;
import java.util.List;

public class Explore extends Fragment implements ExploreView {
    FragmentExploreBinding binding;

    DiscoverNewPlacesAdapter discoverNewPlacesAdapter;
    PopularthisWeekAdapter popularThisWeek;
    CuisineCategoryAdapter cuisineCategoryAdapter;

    ExplorePresenter shopspresenter;

    HashMap<String, String> map = new HashMap();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(getLayoutInflater(), container, false);


        shopspresenter = new ExplorePresenter(this);
        if (Common.isConnected()) {
            map = new HashMap();
            if (GlobalData.users != null)
                map.put("user_id", String.valueOf(GlobalData.users.getId()));
            else
                map.put("user_id", "1");
            map.put("latitude", String.valueOf(GlobalData.latitude));
            map.put("longitude", String.valueOf(GlobalData.longitude));
            shopspresenter.shops(map);


            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.nsv1.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.GONE);
        } else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);


            binding.nsv1.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.GONE);
        }

        binding.etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        return binding.getRoot();
    }


    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {
        if (cuisineResponseItems.isEmpty()) {
            binding.recyclerCategories.setVisibility(View.GONE);
            binding.tvCategories.setVisibility(View.GONE);
        } else {
            cuisineCategoryAdapter = new CuisineCategoryAdapter(getActivity(), cuisineResponseItems, new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {
                    Intent intent = new Intent(getActivity(), ClickCuisineActivity.class);
                    intent.putExtra("cuisine", data);
                    startActivity(intent);
                }
            });
            binding.recyclerCategories.setAdapter(cuisineCategoryAdapter);
            binding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        }

        shopspresenter.shopsPopular(map);
    }

    @Override
    public void onSuccessShops(List<Shop> shopsItemList) {
        if (shopsItemList.isEmpty()) {
            binding.recyclerDiscoverNewPlace.setVisibility(View.GONE);
            binding.tvNewPlace.setVisibility(View.GONE);
        } else {
            discoverNewPlacesAdapter = new DiscoverNewPlacesAdapter(getActivity(), shopsItemList, new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {

                    Intent intent = new Intent(getActivity(), ShopsDetailsActivity.class);
                    intent.putExtra("ShopId", data);
                    intent.putExtra("pathImage", path);
                    startActivity(intent);
                }
            });
            binding.recyclerDiscoverNewPlace.setAdapter(discoverNewPlacesAdapter);
            binding.recyclerDiscoverNewPlace.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        }
        shopspresenter.cuisine();
    }

    @Override
    public void onSuccessShopsPopular(List<Shop> shopsItemList) {
        if (shopsItemList.isEmpty()) {
            binding.recyclerPopularWeek.setVisibility(View.GONE);
            binding.tvPopularWeek.setVisibility(View.GONE);
        } else {

            popularThisWeek = new PopularthisWeekAdapter(getActivity(), shopsItemList, new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {
                    Intent intent = new Intent(getActivity(), ShopsDetailsActivity.class);
                    intent.putExtra("ShopId", data);
                    intent.putExtra("pathImage", path);
                    startActivity(intent);
                }
            });
            binding.recyclerPopularWeek.setAdapter(popularThisWeek);
            binding.recyclerPopularWeek.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.GONE);
        binding.layoutNodata.clNoData.setVisibility(View.GONE);
        binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
        binding.nsv1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorShops() {
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutNodata.clNoData.setVisibility(View.GONE);
        binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

        binding.recyclerDiscoverNewPlace.setVisibility(View.GONE);
        binding.tvNewPlace.setVisibility(View.GONE);
    }

    @Override
    public void showProgressShops() {

    }

    @Override
    public void dismissProgressShops() {

    }

    //shopsDetails

}