package com.example.urban_food.Activites.MyProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityProfileViewBinding;
import com.example.urban_food.fragment.profile.Profile;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileDetailActivity extends AppCompatActivity implements PickiTCallbacks, ProfileDetailView {
    ActivityProfileViewBinding binding;
    int SELECT_PICTURE = 15;
    String savedimg = "";
    PickiT pickiT;
    ProfileDetailPresenter profileDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityProfileViewBinding.inflate(getLayoutInflater());
        profileDetailPresenter = new ProfileDetailPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        pickiT = new PickiT(this, this, this);
        binding.imgCamera.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICTURE);


        });


        if (GlobalData.users != null) {

            binding.etUsername1.setText(GlobalData.users.getName());
            binding.etEmail1.setText(GlobalData.users.getEmail());
            binding.etPhone.setText(GlobalData.users.getPhone());
            binding.etPhone.setEnabled(false);
            if (GlobalData.users.getAvatar() == null) {
                Glide.with(this)
                        .load(R.drawable.ic_myprofile)
                        .circleCrop()
                        .into(binding.ivProfileAvatar);
            } else {
                Glide.with(this)
                        .load(GlobalData.users.getAvatar())
                        .circleCrop()
                        .into(binding.ivProfileAvatar);
            }
        } else {
            Glide.with(this)
                    .load(R.drawable.ic_myprofile)
                    .circleCrop()
                    .into(binding.ivProfileAvatar);
        }


        binding.btnUpdatebtn.setOnClickListener(view -> {
            if (binding.etUsername1.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter UserName", Toast.LENGTH_SHORT).show();
            } else {


                File file  = new File(savedimg);

                RequestBody imageBody = RequestBody.create(file, MediaType.get("image/*"));

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("name", binding.etUsername1.getText().toString())
                        .addFormDataPart("email", binding.etEmail1.getText().toString())
                        .addFormDataPart("avatar", file.getName(), imageBody)
                        .build();

                profileDetailPresenter.updateProfile(requestBody);
            }
        });

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
    public void onSuccessChange(String msg) {
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Profile.class));
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
}