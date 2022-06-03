package com.example.urban_food.Activities.MyProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avatarfirst.avatargenlib.AvatarGenerator;
import com.bumptech.glide.Glide;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityProfileViewBinding;
import com.example.urban_food.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileDetailActivity extends AppCompatActivity implements PickiTCallbacks, ProfileDetailView {
    ActivityProfileViewBinding binding;
    int SELECT_PICTURE = 15;
    String savedimg = "";
    PickiT pickiT;
    ProfileDetailPresenter profileDetailPresenter;
    private String device_id;
    private String fcm_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityProfileViewBinding.inflate(getLayoutInflater());
        profileDetailPresenter = new ProfileDetailPresenter(this);
        getDeviceIdAndToken();
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        pickiT = new PickiT(this, this, this);
        binding.imgCamera.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICTURE);


        });
        binding.ivBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        if (GlobalData.users != null) {
            binding.imgCamera.setVisibility(View.GONE);
            binding.etUsername1.setText(GlobalData.users.getName());
            binding.etEmail1.setText(GlobalData.users.getEmail());
            binding.etPhone.setText(GlobalData.users.getPhone());
            binding.etPhone.setEnabled(false);

            Glide.with(this)
                    .load("https://brokenfortest")
                    .placeholder(new AvatarGenerator.AvatarBuilder(this)
                            .setLabel(GlobalData.users.getName())
                            .setAvatarSize(250)
                            .setTextSize(63)
                            .toCircle()
                            .build())
                    .into(binding.ivProfileAvatar);
        }


        binding.btnUpdatebtn.setOnClickListener(view -> {
            if (binding.etUsername1.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter UserName", Toast.LENGTH_SHORT).show();
            } else {

/*

                File file  = new File(savedimg);

                RequestBody imageBody = RequestBody.create(file, MediaType.get("image/*"));

*/
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("name", binding.etUsername1.getText().toString())
                        .addFormDataPart("email", binding.etEmail1.getText().toString())
/*
                        .addFormDataPart("avatar", file.getName(), imageBody)
*/
                        .build();

                profileDetailPresenter.updateProfile(requestBody);
            }
        });

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
            device_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            device_id = "";
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
        }
    }

    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        savedimg = path;
        Glide
                .with(ProfileDetailActivity.this)
                .load(savedimg)
                .circleCrop()
                .into(binding.ivProfileAvatar);
    }

    @Override
    public void PickiTonMultipleCompleteListener(ArrayList<String> paths, boolean wasSuccessful, String Reason) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onSuccessChange(User user) {


        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
        GlobalData.users=user;
        onBackPressed();


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