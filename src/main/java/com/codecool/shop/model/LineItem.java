package com.codecool.shop.model;

import java.util.Currency;

public class LineItem {
    private int id;
    private String name;
    private float defaultPrice;
    private Currency defaultCurrency;
    private int quantity;

    public LineItem(Product product) {
        id = product.getId();
        name = product.getName();
        defaultPrice = product.getDefaultPrice();
        defaultCurrency = product.getDefaultCurrency();
        quantity = 1;
    }

    public void addLineItem(){
        quantity += 1;
    }

    public void removeLineItem(){
        quantity -= 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDefaultPriceForOne() {
        return defaultPrice;
    }

    public float getDefaultPriceSum() {
        return defaultPrice*quantity;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultPrice=" + defaultPrice +
                ", defaultCurrency=" + defaultCurrency +
                ", quantity=" + quantity +
                '}';
    }
}
