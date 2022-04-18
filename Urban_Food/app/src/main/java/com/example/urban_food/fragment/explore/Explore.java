package com.example.urban_food.fragment.explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Adapter.CuisineCategoryAdapter;
import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.Modal.ExploreModal.ShopsItem;
import com.example.urban_food.databinding.FragmentExploreBinding;

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
        shopspresenter.shops(21.1702,72.8311);
        shopspresenter.cuisine();
        return binding.getRoot();
    }


    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {
        cuisineCategoryAdapter = new CuisineCategoryAdapter(getActivity(),cuisineResponseItems);
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