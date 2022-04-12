package com.example.urban_food.fragment.explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Modal.ShopModal.ShopsItem;
import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentExploreBinding;

public class Explore extends Fragment implements ShopsView {
    FragmentExploreBinding binding;

    DiscoverNewPlacesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentExploreBinding.inflate(getLayoutInflater(),container,false);



        return binding.getRoot();
    }

    @Override
    public void onSuccessShops() {

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