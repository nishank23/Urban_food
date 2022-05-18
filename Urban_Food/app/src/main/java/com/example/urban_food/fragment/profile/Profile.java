package com.example.urban_food.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avatarfirst.avatargenlib.AvatarGenerator;
import com.bumptech.glide.Glide;
import com.example.urban_food.Activities.ChangePassowrdScreen.ChangePassword;
import com.example.urban_food.Activities.ManageAddress.ManageAddress;
import com.example.urban_food.Activities.MyProfile.ProfileDetailActivity;
import com.example.urban_food.Activities.MyProfile.ProfileDetailPresenter;
import com.example.urban_food.Activities.MyProfile.ProfileDetailView;
import com.example.urban_food.Activities.PastOrder.PastOrder;
import com.example.urban_food.Activities.Wallet.Wallet;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.FragmentProfileBinding;
import com.example.urban_food.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class Profile extends Fragment implements ProfileDetailView {

    FragmentProfileBinding binding;
    ProfileDetailPresenter presenter = new ProfileDetailPresenter(this);
    private String device_id;
    private String fcm_token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
  /*      getDeviceIdAndToken();
        HashMap<String, String> map = new HashMap<>();
        map.put("device_type", "android");
        map.put("device_id", device_id);
        map.put("device_token", fcm_token);
        presenter.getProfile(map);
*/
        if (GlobalData.users != null) {
            binding.tvProfileName.setText(GlobalData.users.getName());
            Glide.with(getContext())
                    .load("https://brokenfortest")
                    .placeholder(new AvatarGenerator.AvatarBuilder(getActivity())
                            .setLabel(GlobalData.users.getName())
                            .setAvatarSize(250)
                            .setTextSize(63)
                            .toCircle()
                            .build())
                    .into(binding.ivImage);

        }
        binding.constraintChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);

            }
        });
        binding.constraintMyorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PastOrder.class);
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

        binding.constraintWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new  Intent(getActivity(), Wallet.class);
                startActivity(intent);
            }
        });

        binding.constraintManageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManageAddress.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
    private void getDeviceIdAndToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("LoginActivity", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token

                        // Log and toast
                        fcm_token = task.getResult();

                    }
                });

        try {
            device_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            device_id = "";
        }

    }


    @Override
    public void onResume() {

        super.onResume();
        if (GlobalData.users != null) {
            binding.tvProfileName.setText(GlobalData.users.getName());
            Glide.with(getContext())
                    .load("https://brokenfortest")
                    .placeholder(new AvatarGenerator.AvatarBuilder(getActivity())
                            .setLabel(GlobalData.users.getName())
                            .setAvatarSize(250)
                            .setTextSize(63)
                            .toCircle()
                            .build())
                    .into(binding.ivImage);
        }

    }

    @Override
    public void onSuccessChange(User user) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onSuccessProfile(User user) {


    }

}