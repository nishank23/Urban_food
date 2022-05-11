package com.example.urban_food.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avatarfirst.avatargenlib.AvatarConstants;
import com.avatarfirst.avatargenlib.AvatarGenerator;
import com.bumptech.glide.Glide;
import com.example.urban_food.Activites.ChangePassowrdScreen.ChangePassword;
import com.example.urban_food.Activites.MyProfile.ProfileDetailActivity;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentProfileBinding;


public class Profile extends Fragment {

    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);

        if (GlobalData.users != null) {
            binding.tvProfileName.setText(GlobalData.users.getName());

            Glide.with(getContext())
                    .load("https://brokenfortest")
                    .placeholder(AvatarGenerator.Companion.avatarImage(getActivity(),25,AvatarConstants.Companion.getRECTANGLE(), GlobalData.users.getName()))
                    .into(binding.ivImage);
        }

        binding.constraintChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);

            }
        });

        binding.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getActivity(), ProfileDetailActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }


}