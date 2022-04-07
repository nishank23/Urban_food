package com.example.urban_food.Activites.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.urban_food.Adapter.HomeViewPager;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityHomeBinding;
import com.example.urban_food.fragment.explore.Explore;
import com.example.urban_food.fragment.favorite.Favorite;
import com.example.urban_food.fragment.myorder.MyOrder;
import com.example.urban_food.fragment.profile.Profile;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new Explore());
        fragments.add(new Favorite());
        fragments.add(new MyOrder());
        fragments.add(new Profile());

        ArrayList<Integer> icons=new ArrayList<>();
        icons.add(R.drawable.ic_explore);
        icons.add(R.drawable.ic_favroite);
        icons.add(R.drawable.ic_myorder);
        icons.add(R.drawable.ic_myprofile);

        HomeViewPager adapter=new HomeViewPager(this,fragments);
        binding.homeViewpager.setAdapter(adapter);
        binding.homeViewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        new TabLayoutMediator(binding.homeTab, binding.homeViewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(icons.get(position));
            }
        }).attach();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
/*
        PrefUtils.putBooleanPref(Common.isLoggedIn,true,this);
*/

    }

}