package com.clouway.crm.core.Store;

public abstract class Product implements ISellable {

    private double price;
    private static int quantity;

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double amount){
        this.price = amount;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int amount){
        quantity += amount;
    }

    public double sellFor(){
        setQuantity(-1); //Calling the setQuantity first is a mistake and should be called after the wallet check
        return this.getPrice();
    }

}
