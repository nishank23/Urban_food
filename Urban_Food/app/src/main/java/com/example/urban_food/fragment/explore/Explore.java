package com.example.urban_food.fragment.explore;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activities.Home.HomeActivity;
import com.example.urban_food.Activities.Home.HomeNewActivity;
import com.example.urban_food.Activities.OngoingOrder.OngoingOrderActivity;
import com.example.urban_food.Activities.SearchScreen.SearchActivity;
import com.example.urban_food.Activities.SelectAddress.SelectAddress;
import com.example.urban_food.Activities.ShopsDetail.ClickCuisineActivity;
import com.example.urban_food.Activities.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.Adapter.CuisineCategoryAdapter;
import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.BottomsheetHomeLayoutBinding;
import com.example.urban_food.databinding.BottomsheetOngoingOrderBinding;
import com.example.urban_food.databinding.FragmentExploreBinding;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.RestaurantsData;
import com.example.urban_food.model.Shop;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;
import java.util.List;

public class Explore extends Fragment implements ExploreView {
    FragmentExploreBinding binding;
    Dialog dialog;
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


        binding.tvAddresslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelectAddress.class));

            }
        });
        /*if (Common.isConnected()) {
            map = new HashMap();
            if (GlobalData.users != null)
                map.put("user_id", String.valueOf(GlobalData.users.getId()));
            else

                map.put("user_id", "1");


            map.put("latitude", String.valueOf(GlobalData.userAddressSelect.getLatitude()));
            map.put("longitude", String.valueOf(GlobalData.userAddressSelect.getLongitude()));
            Log.d("let_long_sel",map.toString());
            shopspresenter.shops(map);


            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.nsv1.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.GONE);
            binding.ivAddress.setVisibility(View.GONE);
            binding.tvAddresslist.setVisibility(View.GONE);
        } else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);


            binding.nsv1.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.GONE);

            binding.ivAddress.setVisibility(View.GONE);
            binding.tvAddresslist.setVisibility(View.GONE);
        }*/

        binding.etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        return binding.getRoot();
    }


    void bottom() {
        binding.bottomsheet.main.setVisibility(View.VISIBLE);
        binding.bottomsheet.ongoingviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OngoingOrderActivity.class));

            }
        });
    }

    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {
        if (cuisineResponseItems.isEmpty()) {
            binding.recyclerCategories.setVisibility(View.GONE);
            binding.tvCategories.setVisibility(View.GONE);
        } else {

            binding.recyclerCategories.setVisibility(View.VISIBLE);
            binding.tvCategories.setVisibility(View.VISIBLE);
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
    public void onSuccessShops(RestaurantsData shopsItemList) {
        if (shopsItemList.getShops().isEmpty()) {
            binding.recyclerDiscoverNewPlace.setVisibility(View.GONE);
            binding.bottomsheet.main.setVisibility(View.GONE);
            binding.tvNewPlace.setVisibility(View.GONE);
            binding.recyclerCategories.setVisibility(View.GONE);
            binding.tvCategories.setVisibility(View.GONE);
            binding.tvAddresslist.setText(GlobalData.userAddressSelect.getMapAddress());
            binding.ivSearch.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutNotfound.clNoFound.setVisibility(View.VISIBLE);

        } else {

            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNotfound.clNoFound.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.VISIBLE);
            binding.etSearch.setVisibility(View.VISIBLE);
            binding.recyclerCategories.setVisibility(View.VISIBLE);
            binding.tvCategories.setVisibility(View.VISIBLE);
            binding.recyclerDiscoverNewPlace.setVisibility(View.VISIBLE);
            binding.bottomsheet.main.setVisibility(View.VISIBLE);
            binding.tvNewPlace.setVisibility(View.VISIBLE);

            shopspresenter.cuisine();
            discoverNewPlacesAdapter = new DiscoverNewPlacesAdapter(getActivity(), shopsItemList.getShops(), new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {
                    Intent intent = new Intent(getActivity(), ShopsDetailsActivity.class);
                    intent.putExtra("ShopId", data);
                    intent.putExtra("pathImage", path);
                    startActivity(intent);
                }
            });
            if (shopsItemList.getCurrentOrder() != null) {
                binding.bottomsheet.carttext.setText("Ongoing Order :" + shopsItemList.getCurrentOrder().getStatus().toLowerCase());
                bottom();

            } else {
                binding.bottomsheet.main.setVisibility(View.GONE);
            }

            if (GlobalData.userAddressSelect.getMapAddress() != null)
                binding.tvAddresslist.setText(GlobalData.userAddressSelect.getMapAddress());
            else
                binding.tvAddresslist.setText(GlobalData.userAddressSelect.getMapAddress());




            binding.recyclerDiscoverNewPlace.setAdapter(discoverNewPlacesAdapter);
            binding.recyclerDiscoverNewPlace.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        }

    }


    @Override
    public void onSuccessShopsPopular(List<Shop> shopsItemList) {
        if (shopsItemList.isEmpty()) {
            binding.recyclerPopularWeek.setVisibility(View.GONE);
            binding.tvPopularWeek.setVisibility(View.GONE);
        } else {

            binding.recyclerPopularWeek.setVisibility(View.VISIBLE);
            binding.tvPopularWeek.setVisibility(View.VISIBLE);
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
        binding.etSearch.setVisibility(View.VISIBLE);
        binding.ivSearch.setVisibility(View.VISIBLE);

        binding.ivAddress.setVisibility(View.VISIBLE);
        binding.tvAddresslist.setVisibility(View.VISIBLE);
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


    @Override
    public void onResume() {
        super.onResume();
        if (Common.isConnected()) {
            map = new HashMap();
            if (GlobalData.users != null)
                map.put("user_id", String.valueOf(GlobalData.users.getId()));
            else
                map.put("user_id", "1");
            map.put("latitude", String.valueOf(GlobalData.userAddressSelect.getLatitude()));
            map.put("longitude", String.valueOf(GlobalData.userAddressSelect.getLongitude()));
            Log.d("let_long_sel",map.toString());
            shopspresenter.shops(map);

            binding.layoutNotfound.clNoFound.setVisibility(View.GONE);
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


    }
}