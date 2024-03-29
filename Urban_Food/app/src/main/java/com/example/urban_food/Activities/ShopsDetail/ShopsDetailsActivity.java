package com.example.urban_food.Activities.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.urban_food.Activities.ShopsDetail.cart.CartPresenter;
import com.example.urban_food.Activities.ShopsDetail.cart.CartView;
import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.Adapter.MenuAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityShopsDetailsBinding;
import com.example.urban_food.fragment.favorite.FavoritePresenter;
import com.example.urban_food.fragment.favorite.FavoriteView;
import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Available;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.Category;
import com.example.urban_food.model.FavoriteList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopsDetailsActivity extends AppCompatActivity implements ShopDetailsView, CartView, FavoriteView {

    ActivityShopsDetailsBinding binding;
    String shopId = "";
    String pathImage = "";
    int cartProductId;
    int cartQty;
    int cartId;
    String favmsg = "";
    List<Available> availableList = new ArrayList<>();
    CartPresenter cartPresenter;
    ShopDetailsPresenter shopDetailsPresenter;
    FavoritePresenter favoritePresenter;
    ProgressDialog progressDialog;

    boolean checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shopId = getIntent().getStringExtra("ShopId");
        favoritePresenter = new FavoritePresenter(ShopsDetailsActivity.this);

        pathImage = getIntent().getStringExtra("pathImage");
        shopDetailsPresenter = new ShopDetailsPresenter(this);
        if (Common.isConnected()) {
            binding.ivBackShopsDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
            binding.nsv1.setVisibility(View.GONE);

            HashMap<String, String> map = new HashMap();
            map.put("shop", shopId);
            map.put("user_id", String.valueOf(GlobalData.users.getId()));
            shopDetailsPresenter.getShopDetails(map);
            favoritePresenter.getFavorite();

            binding.ivCart.cartlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                    GlobalData.pastorder=true;
                }
            });


            binding.ivFavrouite.setOnClickListener(view -> {
                if (!checker) {
                    favoritePresenter.addFavorite(shopId);
/*
                    Toast.makeText(ShopsDetailsActivity.this, "" + checker, Toast.LENGTH_SHORT).show();
*/
                    // Common.showToast(favmsg);
                } else {
/*
                    Toast.makeText(ShopsDetailsActivity.this, "" + checker, Toast.LENGTH_SHORT).show();
*/
                    android.app.AlertDialog.Builder dialog = new AlertDialog.Builder(ShopsDetailsActivity.this);
                    dialog.setTitle("Delete Favorite");
                    dialog.setMessage("Do you want to remove the Favorite");
                    dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                        favoritePresenter.deleteFavorite(shopId);

                    });
                    dialog.show();

                }
            });


        } else {

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
    public void onSuccessShopDetails(List<Category> shopDetailList) {


        if (shopDetailList.isEmpty()) {
            binding.ivCart.cartlay.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.nsv1.setVisibility(View.GONE);
            binding.ivShopShopsDetail.setVisibility(View.GONE);
            binding.ivBackShopsDetail.setVisibility(View.GONE);
            binding.tvFullMenu.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
        } else {
            binding.ivCart.cartlay.setVisibility(View.VISIBLE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.nsv1.setVisibility(View.VISIBLE);

            binding.ivShopShopsDetail.setVisibility(View.VISIBLE);
            binding.ivBackShopsDetail.setVisibility(View.VISIBLE);
            binding.tvFullMenu.setVisibility(View.VISIBLE);
            binding.rvMenu.setVisibility(View.VISIBLE);

            Glide
                    .with(this)
                    .load(pathImage)
                    .into(binding.ivShopShopsDetail);
            int quantity = 0;
            for (int i = 0; i < shopDetailList.get(0).getProducts().size(); i++) {

                if (!shopDetailList.get(0).getProducts().get(i).getCart().isEmpty()) {
                    quantity = quantity + shopDetailList.get(0).getProducts().get(i).getCart().get(0).getQuantity();
                }
            }
            if (quantity == 0) {
                binding.ivCart.cartlay.setVisibility(View.GONE);
            } else {
                binding.ivCart.cartlay.setVisibility(View.VISIBLE);
                binding.ivCart.badge.setText(String.valueOf(quantity));
            }



/*
            Log.d("checking",""+String.valueOf(shopDetailList.get(0).getProducts().get(0).getCart().get(0).getQuantity())+" "+String.valueOf(shopDetailList.get(0).getProducts().get(1).getCart().get(0).getQuantity())+" "+String.valueOf(shopDetailList.get(0).getProducts().get(2).getCart().get(0).getQuantity()));
*/

            MenuAdapter menuAdapter = new MenuAdapter(this, shopDetailList, new RvMenuInterface() {
                @Override
                public void cartParaWithCardId(int id, int value, String CartValue) {
                    cartProductId = id;
                    cartQty = value;
                    cartId = Integer.parseInt(CartValue);
                    cartPresenter = new CartPresenter(ShopsDetailsActivity.this);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("product_id", String.valueOf(cartProductId));
                    map.put("quantity", String.valueOf(cartQty));
                    map.put("cart_id", String.valueOf(cartId));
                    cartPresenter.callCart(map);
                }

                @Override
                public void cartPara(int id, int value) {
                    cartProductId = id;
                    cartQty = value;
                    cartPresenter = new CartPresenter(ShopsDetailsActivity.this);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("product_id", String.valueOf(cartProductId));
                    map.put("quantity", String.valueOf(cartQty));
                    cartPresenter.callCart(map);
                    cartPresenter.getCallCart();

                }

                @Override
                public void clearCartPara(Boolean check, int id, int value) {
                    GlobalData.Cart.clear();
                    cartProductId = id;
                    cartQty = value;
                    cartPresenter = new CartPresenter(ShopsDetailsActivity.this);
                    cartPresenter.getClearCart();
                }
            });
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

    @Override
    public void onSuccessCartView(AddCart cartResponse) {
        GlobalData.Cart = cartResponse.getProducts();

        int quantity = 0;
        if (cartResponse.getProducts().size() == 0) {
            binding.ivCart.cartlay.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < cartResponse.getProducts().size(); i++) {

                quantity = quantity + cartResponse.getProducts().get(i).getQuantity();
            }
            binding.ivCart.cartlay.setVisibility(View.VISIBLE);
            binding.ivCart.badge.setText(String.valueOf(quantity));
        }

    }

    @Override
    public void onErrorCartView() {

    }

    @Override
    public void onSuccessGetCartView(AddCart getCartResponse) {
        GlobalData.Cart = getCartResponse.getProducts();


        int quantity = 0;
        if (getCartResponse.getProducts().size() == 0) {
            binding.ivCart.cartlay.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < getCartResponse.getProducts().size(); i++) {

                quantity = quantity + getCartResponse.getProducts().get(i).getQuantity();
            }
            binding.ivCart.cartlay.setVisibility(View.VISIBLE);
            binding.ivCart.badge.setText(String.valueOf(quantity));
        }
    }

    @Override
    public void onSuccessGetClearCartView(String message) {
        cartPresenter = new CartPresenter(ShopsDetailsActivity.this);
        HashMap<String, String> map = new HashMap<>();
        map.put("product_id", String.valueOf(cartProductId));
        map.put("quantity", String.valueOf(cartQty));
        cartPresenter.callCart(map);

    }

    @Override
    public void showProgressShops() {
        progressDialog = new ProgressDialog(this);
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
    public void onSuccessFavorite(String msg) {
        checker = true;
        binding.ivFavrouite.setImageResource(R.drawable.ic_iconmonstr_bookmark_43);
        Toast.makeText(this, "Added as favourite", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getFavorite(FavoriteList response) {
        /*checker=false;*/
        availableList = response.getAvailable();
        for (int i = 0; i < availableList.size(); i++) {
            if (String.valueOf(availableList.get(i).getShopId()).equals(shopId)) {
                checker = true;
            }
        }
        binding.ivFavrouite.setImageResource(checker ? R.drawable.ic_iconmonstr_bookmark_43 : R.drawable.ic_favroite);

    }

    @Override
    public void deleteFavorite(String msg) {
        checker = false;
        Toast.makeText(this, "Favorite Removed", Toast.LENGTH_SHORT).show();
        binding.ivFavrouite.setImageResource(R.drawable.ic_favroite);
    }

    @Override
    public void onErrorShops() {

    }
}