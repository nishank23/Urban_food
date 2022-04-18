package com.example.urban_food.fragment.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activites.ShopsDetail.ClickCuisineActivity;
import com.example.urban_food.Adapter.CuisineCategoryAdapter;
import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Adapter.PopularthisWeekAdapter;
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

        shopspresenter = new ExplorePresenter(this);
        HashMap<String,String> map=new HashMap();
        map.put("user_id","1");
        map.put("latitude", String.valueOf(GlobalData.latitude));
        map.put("longitude",String.valueOf(GlobalData.longitude));
        shopspresenter.shops(map);
        shopspresenter.cuisine();
        return binding.getRoot();
    }


    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {
        cuisineCategoryAdapter = new CuisineCategoryAdapter(getActivity(), cuisineResponseItems, new CuisineInterface() {
            @Override
            public void cuisineItem(String data) {
                Intent intent=new Intent(getActivity(), ClickCuisineActivity.class);
                intent.putExtra("cuisine",data);
                startActivity(intent);
            }
        });
        binding.recyclerCategories.setAdapter(cuisineCategoryAdapter);
        binding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


    }

    @Override
    public void onSuccessShops(List<ShopsItem> shopsItemList) {

        discoverNewPlacesAdapter = new DiscoverNewPlacesAdapter(getActivity(),shopsItemList);
        binding.recyclerDiscoverNewPlace.setAdapter(discoverNewPlacesAdapter);
        binding.recyclerDiscoverNewPlace.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        popularThisWeek = new PopularthisWeekAdapter(getActivity(),shopsItemList);
        binding.recyclerPopularWeek.setAdapter(popularThisWeek);
        binding.recyclerPopularWeek.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onErrorShops() {

    }

    @Override
    public void showProgressShops() {

    }

    @Override
    public void dismissProgressShops() {

    }
}