package com.example.urban_food.fragment.explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Adapter.DiscoverNewPlacesAdapter;
import com.example.urban_food.Modal.ShopModal.ShopsItem;
import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentExploreBinding;

import java.util.ArrayList;
import java.util.List;

public class Explore extends Fragment implements ShopsView {
    FragmentExploreBinding binding;

    DiscoverNewPlacesAdapter discoverNewPlacesAdapter;

    ShopsPresenter shopspresenter;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentExploreBinding.inflate(getLayoutInflater(),container,false);

        shopspresenter = new ShopsPresenter(this);
        shopspresenter.shops(String.valueOf(1),21.1702,72.8311);

        return binding.getRoot();
    }


    @Override
    public void onSuccessShops(List<ShopsItem> shopsItemList) {

        discoverNewPlacesAdapter = new DiscoverNewPlacesAdapter(getActivity(),shopsItemList);
        binding.recyclerDiscoverNewPlace.setAdapter(discoverNewPlacesAdapter);
        binding.recyclerDiscoverNewPlace.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

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