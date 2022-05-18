package com.example.urban_food.Activities.PastOrder;

import com.example.urban_food.model.Order;

import java.util.List;

public interface OrderView {
    void getOrder(List<Order> orderList);
    void showProgress();
    void dismissProgress();


/*
    void orderDetail(String id);
*/

}
