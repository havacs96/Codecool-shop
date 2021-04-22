package com.codecool.shop.order;

public class Card {
    private String cardNumber;
    private String cardHolder;
    private int cvv;

    public Card(String cardNumber, String cardHolder, int cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public int getCvv() {
        return cvv;
    }
}
