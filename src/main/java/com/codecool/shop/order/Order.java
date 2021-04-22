package com.codecool.shop.order;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private List<LineItem> data = new ArrayList<>();
    private CheckoutData checkoutData;


   public void setId(int id) {
        this.id = id;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public void add(LineItem product) {
        data.add(product);
    }

    public LineItem find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id) {
        data.remove(find(id));
    }

    public List<LineItem> getAll() {
        return data;
    }

    public void setCheckoutData(CheckoutData checkoutData) {
        this.checkoutData = checkoutData;
    }

    public List<LineItem> getData() {
        return data;
    }

    public CheckoutData getCheckoutData() {
        return checkoutData;
    }
}
