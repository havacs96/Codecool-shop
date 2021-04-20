package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

import java.util.List;

public interface OrderDao {

    void add(LineItem item);
    LineItem find(int id);
    void remove(int id);

    List<LineItem> getAll();
}