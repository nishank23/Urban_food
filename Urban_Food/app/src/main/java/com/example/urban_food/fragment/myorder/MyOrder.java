package com.example.urban_food.fragment.myorder;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.urban_food.R;
import com.example.urban_food.databinding.FragmentMyOrderBinding;

public class MyOrder extends Fragment {
    FragmentMyOrderBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMyOrderBinding.inflate(getLayoutInflater(),container,false);

        return binding.getRoot();
    }
}