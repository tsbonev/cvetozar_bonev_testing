package com.clouway.crm.core.Store;

public class Clerk extends Worker {

    public double SellTo(ISellable product){
        System.out.println(product.getClass().getSimpleName() + " has been sold");
        return product.sellFor();
    }
}
