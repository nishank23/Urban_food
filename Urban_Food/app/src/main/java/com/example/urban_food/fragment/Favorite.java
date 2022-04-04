package com.example.urban_food.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentFavoriteBinding;

public class Favorite extends Fragment {

   FragmentFavoriteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFavoriteBinding.inflate(getLayoutInflater(),container,false);



        return binding.getRoot();
    }
}