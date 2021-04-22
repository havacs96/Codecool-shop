package com.codecool.shop.dao;

import com.codecool.shop.order.CheckoutData;
import com.codecool.shop.order.LineItem;

import java.util.List;

public interface OrderDao {

    void add(LineItem item);
    LineItem find(int id);
    void remove(int id);
    void setCheckoutData(CheckoutData checkoutData);
    List<LineItem> getAll();
}