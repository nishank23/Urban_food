package com.example.urban_food.fragment.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activites.ShopsDetail.ClickCuisineActivity;
import com.example.urban_food.Activites.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.Adapter.CuisineCategoryAdapter;
import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.Modal.ExploreModal.ShopsItem;
import com.example.urban_food.databinding.FragmentExploreBinding;

import java.util.HashMap;
import java.util.List;

public class Explore extends Fragment implements ExploreView {
    FragmentExploreBinding binding;

    DiscoverNewPlacesAdapter discoverNewPlacesAdapter;
    PopularthisWeekAdapter popularThisWeek;
    CuisineCategoryAdapter cuisineCategoryAdapter;


    ExplorePresenter shopspresenter;

    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentExploreBinding.inflate(getLayoutInflater(),container,false);



        if(Common.isConnected()){
            shopspresenter = new ExplorePresenter(this);
            HashMap<String,String> map=new HashMap();
            map.put("user_id","1");
            map.put("latitude", String.valueOf(GlobalData.latitude));
            map.put("longitude",String.valueOf(GlobalData.longitude));
            shopspresenter.shops(map);
            shopspresenter.cuisine();


            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.nsv1.setVisibility(View.VISIBLE);
            binding.etSearch.setVisibility(View.VISIBLE);
            binding.ivSearch.setVisibility(View.VISIBLE);
        }
        else{

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);


            binding.nsv1.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }


    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {
        if(cuisineResponseItems.isEmpty()){

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.recyclerCategories.setVisibility(View.GONE);
            binding.tvCategories.setVisibility(View.GONE);
        }else{
            cuisineCategoryAdapter = new CuisineCategoryAdapter(getActivity(), cuisineResponseItems, new ExploreInterface() {
                @Override
                public void cuisineItem(String data,String path) {
                    Intent intent=new Intent(getActivity(), ClickCuisineActivity.class);
                    intent.putExtra("cuisine",data);
                    startActivity(intent);
                }
            });
            binding.recyclerCategories.setAdapter(cuisineCategoryAdapter);
            binding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.recyclerCategories.setVisibility(View.VISIBLE);
            binding.tvCategories.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onSuccessShops(List<ShopsItem> shopsItemList) {
        if(shopsItemList.isEmpty()){

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.recyclerDiscoverNewPlace.setVisibility(View.GONE);
            binding.tvNewPlace.setVisibility(View.GONE);
            binding.recyclerPopularWeek.setVisibility(View.GONE);
            binding.tvPopularWeek.setVisibility(View.GONE);
        }else{
            discoverNewPlacesAdapter = new DiscoverNewPlacesAdapter(getActivity(), shopsItemList, new ExploreInterface() {
                @Override
                public void cuisineItem(String data,String path) {

                    Intent intent = new Intent(getActivity(), ShopsDetailsActivity.class);
                    intent.putExtra("ShopId",data);
                    intent.putExtra("pathImage",path);
                    startActivity(intent);
                }
            });
            binding.recyclerDiscoverNewPlace.setAdapter(discoverNewPlacesAdapter);
            binding.recyclerDiscoverNewPlace.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


            popularThisWeek = new PopularthisWeekAdapter(getActivity(),shopsItemList);
            binding.recyclerPopularWeek.setAdapter(popularThisWeek);
            binding.recyclerPopularWeek.setLayoutManager(new LinearLayoutManager(getActivity()));


            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.recyclerDiscoverNewPlace.setVisibility(View.VISIBLE);
            binding.tvNewPlace.setVisibility(View.VISIBLE);
            binding.recyclerPopularWeek.setVisibility(View.VISIBLE);
            binding.tvPopularWeek.setVisibility(View.VISIBLE);
        }
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