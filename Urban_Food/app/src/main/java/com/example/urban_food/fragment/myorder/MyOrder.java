package com.example.urban_food.fragment.myorder;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urban_food.Activities.ShopsDetail.cart.CartPresenter;
import com.example.urban_food.Activities.ShopsDetail.cart.CartView;
import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.Adapter.CartAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.databinding.FragmentMyOrderBinding;
import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Cart;

import java.util.HashMap;
import java.util.List;

public class MyOrder extends Fragment  implements CartView{
    FragmentMyOrderBinding binding;
    int cartProductId;
    int cartQty;
    int cartId;
    CartPresenter cartPresenter=new CartPresenter(this);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMyOrderBinding.inflate(getLayoutInflater(),container,false);

        if(Common.isConnected()){
            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);

            cartPresenter.getCallCart();
        }else{

            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
            binding.layoutNoInternet.layoutNoInternet.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }


    @Override
    public void onSuccessCartView(List<Cart> cartResponse) {
        binding.tvPriceSubtotal.setText(String.valueOf(cartResponse.get(0).getProduct().getPrices().getPrice()));
    }

    @Override
    public void onErrorCartView() {
        binding.clActionBar.setVisibility(View.GONE);
        binding.cardItemsMyOrder.setVisibility(View.GONE);
        binding.buttonApplyPromoCode.setVisibility(View.GONE);
        binding.buttonContinue.setVisibility(View.GONE);
        binding.clBillList.setVisibility(View.GONE);
        binding.rvItemDetailsMyOrder.setVisibility(View.GONE);


        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);

    }

    @Override
    public void onSuccessGetCartView(AddCart getCartResponse) {
        if(getCartResponse.getProductList()==null){
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
        }
        else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.rvItemDetailsMyOrder.setVisibility(View.VISIBLE);
            binding.clActionBar.setVisibility(View.VISIBLE);
            binding.cardItemsMyOrder.setVisibility(View.VISIBLE);
            binding.buttonApplyPromoCode.setVisibility(View.VISIBLE);
            binding.buttonContinue.setVisibility(View.VISIBLE);
            binding.clBillList.setVisibility(View.VISIBLE);


            for (int i = 0; i < getCartResponse.getProductList().size(); i++) {
                binding.tvTitleShopName.setText(getCartResponse.getProductList().get(i).getProduct().getShop().getName());
                binding.tvShopLocation.setText(getCartResponse.getProductList().get(i).getProduct().getShop().getAddress());
            }
            Log.d("sizeCArt", "" + getCartResponse.getProductList().size());
            CartAdapter cartAdapter = new CartAdapter(getActivity(), getCartResponse.getProductList(), new RvMenuInterface() {
                @Override
                public void cartParaWithCardId(int id, int value, String CartValue) {
                    cartProductId = id;
                    cartQty = value;
                    cartId = Integer.parseInt(CartValue);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("product_id", String.valueOf(cartProductId));
                    map.put("quantity", String.valueOf(cartQty));
                    map.put("cart_id", String.valueOf(cartId));
                    cartPresenter.callCart(map);
                }

                @Override
                public void cartPara(int id, int value) {
                }

                @Override
                public void clearCartPara(Boolean check, int id, int value) {
                }
            });
            binding.rvItemDetailsMyOrder.setAdapter(cartAdapter);
            binding.rvItemDetailsMyOrder.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.tvPriceSubtotal.setText(String.valueOf(getCartResponse.getProductList().get(0).getProduct().getPrices().getPrice()));
        }
    }

    @Override
    public void onSuccessGetClearCartView(String message) {

    }

    @Override
    public void showProgressShops() {
        binding.clActionBar.setVisibility(View.GONE);
        binding.cardItemsMyOrder.setVisibility(View.GONE);
        binding.buttonApplyPromoCode.setVisibility(View.GONE);
        binding.buttonContinue.setVisibility(View.GONE);
        binding.clBillList.setVisibility(View.GONE);


        binding.layoutError.clError.setVisibility(View.GONE);
        binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);
    }

    @Override
    public void dismissProgressShops() {

        binding.clActionBar.setVisibility(View.VISIBLE);
        binding.cardItemsMyOrder.setVisibility(View.VISIBLE);
        binding.buttonApplyPromoCode.setVisibility(View.VISIBLE);
        binding.buttonContinue.setVisibility(View.VISIBLE);
        binding.clBillList.setVisibility(View.VISIBLE);


        binding.layoutError.clError.setVisibility(View.GONE);
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(Common.isConnected()){
            cartPresenter.getCallCart();
        }else{

            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);


            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
            binding.layoutNoInternet.layoutNoInternet.setVisibility(View.VISIBLE);
        }

    }
}