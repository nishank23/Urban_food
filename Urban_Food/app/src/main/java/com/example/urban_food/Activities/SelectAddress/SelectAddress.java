package com.example.urban_food.Activities.SelectAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.urban_food.Activities.ManageAddress.AddressInterface;
import com.example.urban_food.Activities.ManageAddress.DeleteAddressPresenter;
import com.example.urban_food.Adapter.AddressAdapter;
import com.example.urban_food.Adapter.SelectAddressAdapter;
import com.example.urban_food.databinding.ActivitySelectAddressBinding;
import com.example.urban_food.model.Address;

import java.util.List;

public class SelectAddress extends AppCompatActivity implements AddressInterface {
    ActivitySelectAddressBinding binding;
    DeleteAddressPresenter presenter;
    SelectAddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivitySelectAddressBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        presenter = new DeleteAddressPresenter(this);

        binding.ivBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        presenter.getAddress();

    }

    @Override
    public void ongetAddress(List<Address> address) {
        addressAdapter = new SelectAddressAdapter(this, address);

        binding.rvAddresslist.setAdapter(addressAdapter);
        binding.rvAddresslist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void editClick(Address addressList) {

    }

    @Override
    public void deleteClick(String id) {

    }

    @Override
    public void onDelete(String msg) {

    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}