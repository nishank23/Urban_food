package com.example.urban_food.fragment.favorite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activities.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.Adapter.FavoriteAdapter;
import com.example.urban_food.databinding.FragmentFavoriteBinding;
import com.example.urban_food.fragment.explore.ExploreInterface;
import com.example.urban_food.model.FavoriteList;

public class Favorite extends Fragment implements FavoriteView {

    FragmentFavoriteBinding binding;
    FavoritePresenter favoritePresenter;
    FavoriteAdapter favoriteAdapter;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater(), container, false);

        favoritePresenter = new FavoritePresenter(this);


        favoritePresenter.getFavorite();
        return binding.getRoot();


    }

    @Override
    public void onSuccessFavorite(String msg) {

    }

    @Override
    public void getFavorite(FavoriteList response) {

        if (response.getAvailable().size() != 0) {
            favoriteAdapter = new FavoriteAdapter(getActivity(), response.getAvailable(), new ExploreInterface() {
                @Override
                public void cuisineItem(String data, String path) {
                    Intent intent = new Intent(getActivity(), ShopsDetailsActivity.class);
                    intent.putExtra("ShopId", data);
                    intent.putExtra("pathImage", path);
                    startActivity(intent);

                }
            });
            binding.rvFavorite.setAdapter(favoriteAdapter);
            binding.rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));

        }else{
            binding.rvFavorite.setVisibility(View.GONE);
            binding.tvFavorite.setVisibility(View.GONE);
        }
    }

    @Override
    public void deleteFavorite(String msg) {

    }

    @Override
    public void onErrorShops() {

    }

    @Override
    public void showProgressShops() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void dismissProgressShops() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        favoritePresenter = new FavoritePresenter(this);


        favoritePresenter.getFavorite();
    }
}