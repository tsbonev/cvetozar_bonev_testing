package com.clouway.crm.core.storage;

public class Product {

    private String name;
    private int maxQuantity;
    private int price;

    /**
     * Constructs a product class.
     *
     * @param name of the product
     * @param maxQuantity maximum allowed quantity
     * @param price of the product
     */
    public Product(String name, int maxQuantity, int price){
        this.name = name;
        this.maxQuantity = maxQuantity;
        this.price = price;
    }

    public int getMaxQuantity(){
        return this.maxQuantity;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

}
