package com.example.urban_food.Activities.SearchScreen;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.urban_food.Adapter.SearchProductAdapter;
import com.example.urban_food.Adapter.SearchRestaurantAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivitySearchBinding;
import com.example.urban_food.model.Product;
import com.example.urban_food.model.Search;
import com.example.urban_food.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchActivityView {
    ActivitySearchBinding binding;
    SearchActivityPresenter searchActivityPresenter;
    SearchProductAdapter searchProductAdapter;
    SearchRestaurantAdapter searchRestaurantAdapter;
    List<Product> list = new ArrayList<>();
    List<Shop> shoplist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchActivityPresenter = new SearchActivityPresenter(this);

        binding.chipDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chipDishes.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.orange)));
                binding.chipRestaruant.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.light_grey)));
                binding.rvProduct.setVisibility(View.VISIBLE);
                binding.rvShop.setVisibility(View.GONE);
            }
        });
        binding.chipRestaruant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rvShop.setVisibility(View.VISIBLE);
                binding.rvProduct.setVisibility(View.GONE);
                binding.chipDishes.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.light_grey)));
                binding.chipRestaruant.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.orange)));
            }
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                if (!binding.etSearch.getText().toString().isEmpty()) {
                    String search = binding.etSearch.getText().toString();
                    GlobalData.search = search;
                    searchActivityPresenter.getSearch(search);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }

    @Override
    public void onSuccessSearch(Search response) {


        for (int i = 0; i < response.getProducts().size(); i++) {
            if (response.getProducts().get(i).getName().contains(GlobalData.search)) {
                list = response.getProducts();

            }
        }
        for(int i = 0 ;i <response.getShops().size();i++){
            if(response.getShops().get(i).getName().contains(GlobalData.search)){
                shoplist=response.getShops();
            }
        }

        searchProductAdapter = new SearchProductAdapter(this, list);
        binding.rvProduct.setAdapter(searchProductAdapter);
        binding.rvProduct.setLayoutManager(new LinearLayoutManager(this));



        searchRestaurantAdapter=new SearchRestaurantAdapter(this,shoplist);
        binding.rvShop.setAdapter(searchRestaurantAdapter);
        binding.rvShop.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onError(String msg) {
        Common.showToast(msg);
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}