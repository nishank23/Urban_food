package com.example.urban_food.fragment.explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentExploreBinding;

public class Explore extends Fragment {
    FragmentExploreBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentExploreBinding.inflate(getLayoutInflater(),container,false);



        return binding.getRoot();
    }
}