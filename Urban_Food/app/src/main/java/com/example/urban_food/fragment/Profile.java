package com.example.urban_food.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentProfileBinding;


public class Profile extends Fragment {

    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);

        return binding.getRoot();
    }
}