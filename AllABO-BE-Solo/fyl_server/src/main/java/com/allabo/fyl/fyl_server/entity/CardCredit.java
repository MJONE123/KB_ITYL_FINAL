package com.allabo.fyl.fyl_server.entity;

public class CardCredit {
    private String cardName;
    private String creditLimit;
    private String annualFee;

    public CardCredit(String cardName, String creditLimit, String annualFee) {
        this.cardName = cardName;
        this.creditLimit = creditLimit;
        this.annualFee = annualFee;
    }

    // Getters and Setters
}
