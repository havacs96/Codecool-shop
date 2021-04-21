package com.codecool.shop.order;

public class CheckoutData {

    private String name;
    private String email;
    private long phoneNumber;
    private BillingAddress billingAddress;
    private ShippingAddress shippingAddress;

    public CheckoutData(String name, String email, long phoneNumber, BillingAddress billingAddress, ShippingAddress shippingAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

}

