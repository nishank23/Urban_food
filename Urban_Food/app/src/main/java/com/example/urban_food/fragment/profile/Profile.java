package com.example.urban_food.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        binding.tvProfileName.setText(GlobalData.users.getName());

        if(GlobalData.users.getAvatar()==null){
            Glide .with(getContext())
                    .load(R.drawable.ic_myprofile)
                    .circleCrop()
                    .into(binding.ivImage);
        }else{
            Glide .with(getContext())
                    .load(GlobalData.users.getAvatar())
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