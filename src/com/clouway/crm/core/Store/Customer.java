package com.clouway.crm.core.Store;

import java.util.List;

public class Customer extends Person {

    private List<ISellable> productsOwned;

    public void buy(ISellable product, Clerk clerk){
        this.setWallet(-clerk.SellTo(product));
        this.productsOwned.add(product); //possible check for type and two lists for food and appliances
    }
}
