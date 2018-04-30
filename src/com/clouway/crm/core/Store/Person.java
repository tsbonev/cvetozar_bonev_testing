package com.clouway.crm.core.Store;

public abstract class Person {

    private String name;
    private double height;
    private double wallet;


    public void setWallet(double wallet) {
        this.wallet += wallet;
    }

    public double getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
