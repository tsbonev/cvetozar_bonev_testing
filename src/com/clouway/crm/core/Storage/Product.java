package com.clouway.crm.core.Storage;

public class Product {

    private String name;
    private int maxQuantity;

    public Product(String name, int maxQuantity){
        this.name = name;
        this.maxQuantity = maxQuantity;
    }

    public int getMaxQuantity(){
        return this.maxQuantity;
    }

    public String getName(){
        return this.name;
    }

}
