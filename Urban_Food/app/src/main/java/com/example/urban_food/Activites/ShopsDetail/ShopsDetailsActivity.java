package com.example.urban_food.Activites.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.urban_food.Adapter.MenuAdapter;
import com.example.urban_food.Adapter.RvMenuAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Modal.ShopsDetailsModal.ProductsItem;
import com.example.urban_food.Modal.ShopsDetailsModal.ShopDetailsCategoriesItem;
import com.example.urban_food.databinding.ActivityShopsDetailsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopsDetailsActivity extends AppCompatActivity implements ShopDetailsView {

    ActivityShopsDetailsBinding binding;
    String shopId="";
    String pathImage="";


    ShopDetailsPresenter shopDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShopsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shopId=getIntent().getStringExtra("ShopId");
        pathImage=getIntent().getStringExtra("pathImage");
        shopDetailsPresenter = new ShopDetailsPresenter(this);
        if(Common.isConnected()){

            HashMap<String,String> map=new HashMap();
            map.put("shop", shopId);
            map.put("user_id","1");
            shopDetailsPresenter.getShopDetails(map);


            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.ivShopShopsDetail.setVisibility(View.VISIBLE);
            binding.ivBackShopsDetail.setVisibility(View.VISIBLE);
            binding.tvFullMenu.setVisibility(View.VISIBLE);
            binding.rvMenu.setVisibility(View.VISIBLE);
        }else{

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);


            binding.ivShopShopsDetail.setVisibility(View.GONE);
            binding.ivBackShopsDetail.setVisibility(View.GONE);
            binding.tvFullMenu.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccessShopDetails(List<ShopDetailsCategoriesItem> shopDetailList) {
        if (shopDetailList.isEmpty()) {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.ivShopShopsDetail.setVisibility(View.GONE);
            binding.ivBackShopsDetail.setVisibility(View.GONE);
            binding.tvFullMenu.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
        }else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.ivShopShopsDetail.setVisibility(View.VISIBLE);
            binding.ivBackShopsDetail.setVisibility(View.VISIBLE);
            binding.tvFullMenu.setVisibility(View.VISIBLE);
            binding.rvMenu.setVisibility(View.VISIBLE);

            Glide
                    .with(this)
                    .load(pathImage)
                    .into(binding.ivShopShopsDetail);

            MenuAdapter menuAdapter = new MenuAdapter(this,shopDetailList);
            binding.rvMenu.setAdapter(menuAdapter);
            binding.rvMenu.setLayoutManager(new LinearLayoutManager(this));


        }
    }

    @Override
    public void onErrorShopDetails() {

        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutNodata.clNoData.setVisibility(View.GONE);
        binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


        binding.ivShopShopsDetail.setVisibility(View.GONE);
        binding.ivBackShopsDetail.setVisibility(View.GONE);
        binding.tvFullMenu.setVisibility(View.GONE);
        binding.rvMenu.setVisibility(View.GONE);
    }

    @Override
    public void showProgressShopDetails() {

    }

    @Override
    public void dismissShopDetails() {

    }

}