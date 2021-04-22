package com.codecool.shop.dao;

import com.codecool.shop.order.Order;

import java.util.List;

public interface OrderDao {

    void add(Order item);
    Order find(int id);
    void remove(int id);
    List<Order> getAll();
}