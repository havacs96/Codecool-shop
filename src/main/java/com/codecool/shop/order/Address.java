package com.codecool.shop.order;

public abstract class Address {
    private String country;
    private int zipcode;
    private String city;
    private String address;

    public Address(String country, int zipcode, String city, String address) {
        this.country = country;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
