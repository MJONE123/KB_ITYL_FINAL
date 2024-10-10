package com.allabo.fyl.fyl_server.entity;

public class Deposits {
    private String depositName;
    private String interestRate;
    private String term;

    public Deposits(String depositName, String interestRate, String term) {
        this.depositName = depositName;
        this.interestRate = interestRate;
        this.term = term;
    }

    // Getters and Setters
}
