package com.example.urban_food.Activities.ManageAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.urban_food.Activities.AddAddress.AddAddress;
import com.example.urban_food.Activities.AddAddress.AddAddressPresenter;
import com.example.urban_food.Activities.AddAddress.AddAddressView;
import com.example.urban_food.Adapter.AddressAdapter;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityManageAddressBinding;
import com.example.urban_food.model.Address;

import java.util.List;

public class ManageAddress extends AppCompatActivity implements AddressInterface {
    ActivityManageAddressBinding binding;
    DeleteAddressPresenter presenter;
    AddressAdapter addressAdapter;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new DeleteAddressPresenter(this);
        presenter.getAddress();
        binding.addAddress.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddAddress.class);
            intent.putExtra("isEdit", false);
            startActivity(intent);

        });
        binding.ivBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    public void onDelete(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void ongetAddress(List<Address> address) {

        addressAdapter = new AddressAdapter(this, address, this);

        binding.rvAddresslist.setAdapter(addressAdapter);
        binding.rvAddresslist.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void editClick(Address addressList) {

        GlobalData.editAddress = addressList;
        Intent intent = new Intent(this, AddAddress.class);
        intent.putExtra("isEdit", true);
        startActivity(intent);


    }

    @Override
    public void deleteClick(String id) {
        presenter = new DeleteAddressPresenter(ManageAddress.this);
        presenter.deleteAddress(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new DeleteAddressPresenter(this);
        presenter.getAddress();


    }
}