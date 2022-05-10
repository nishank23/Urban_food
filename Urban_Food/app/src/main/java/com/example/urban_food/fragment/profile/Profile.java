package com.example.urban_food.fragment.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.urban_food.Activites.Login.LoginActivityPresenter;
import com.example.urban_food.Activites.Login.LoginActivityView;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.FragmentProfileBinding;
import com.example.urban_food.model.Address;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.User;

import java.util.HashMap;
import java.util.List;


public class Profile extends Fragment {

    FragmentProfileBinding binding;
    LoginActivityPresenter loginActivityPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        Glide .with(getContext())
                .load(GlobalData.users.getAvatar())
                .into(binding.ivImage);
        binding.tvProfileName.setText(GlobalData.users.getName());

        return binding.getRoot();
    }


}