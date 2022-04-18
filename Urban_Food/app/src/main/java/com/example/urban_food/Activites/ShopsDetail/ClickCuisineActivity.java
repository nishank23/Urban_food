package com.example.urban_food.Activites.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.urban_food.Adapter.PopularthisWeekAdapter;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.Modal.ExploreModal.ShopsItem;
import com.example.urban_food.databinding.ActivityClickCuisineBinding;
import com.example.urban_food.fragment.explore.Explore;
import com.example.urban_food.fragment.explore.ExplorePresenter;
import com.example.urban_food.fragment.explore.ExploreView;

import java.util.HashMap;
import java.util.List;

public class ClickCuisineActivity extends AppCompatActivity implements ExploreView {
    ActivityClickCuisineBinding binding;
    String cuisine_id="";
    ExplorePresenter explorePresenter=new ExplorePresenter(this);
    PopularthisWeekAdapter adapterCuisine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityClickCuisineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cuisine_id=getIntent().getStringExtra("cuisine");
        HashMap<String,String> map=new HashMap();
        map.put("user_id","1");
        map.put("latitude", String.valueOf(GlobalData.latitude));
        map.put("longitude",String.valueOf(GlobalData.longitude));
        map.put("cuisine[" + "" + 0 + "]",cuisine_id);
        explorePresenter.shops(map);


    }

    @Override
    public void onSuccessCuisine(List<Cuisine> cuisineResponseItems) {

    }

    @Override
    public void onSuccessShops(List<ShopsItem> shopsItemList) {
        PopularthisWeekAdapter adapterCuisine=new PopularthisWeekAdapter(this,shopsItemList);
        binding.rvCuisinesShops.setAdapter(adapterCuisine);
        binding.rvCuisinesShops.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onErrorShops() {

    }

    @Override
    public void showProgressShops() {

    }

    @Override
    public void dismissProgressShops() {

    }
}