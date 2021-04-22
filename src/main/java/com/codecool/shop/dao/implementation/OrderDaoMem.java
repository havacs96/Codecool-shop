package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.order.CheckoutData;
import com.codecool.shop.order.LineItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<LineItem> data = new ArrayList<>();
    private CheckoutData checkoutData;
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(LineItem product) {
        data.add(product);
    }

    @Override
    public LineItem find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<LineItem> getAll() {
        return data;
    }

    public void setCheckoutData(CheckoutData checkoutData) {
        this.checkoutData = checkoutData;
    }
}
