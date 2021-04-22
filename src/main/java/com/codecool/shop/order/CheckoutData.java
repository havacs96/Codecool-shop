package com.codecool.shop.order;

public class CheckoutData {

    private String name;
    private String email;
    private BillingAddress billingAddress;
    private ShippingAddress shippingAddress;
    private Card card;

    public CheckoutData(String name, String email, BillingAddress billingAddress, ShippingAddress shippingAddress, Card card) {
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public Card getCard() {
        return card;
    }
}

