package com.clouway.crm.core.Storage;

import com.clouway.crm.core.Storage.StorageExceptions.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Storage {

    private Map<Product, Integer> items;

    public Storage(){
        this.items = new HashMap<Product, Integer>();
    }

    public int getSize(){
        return this.items.size();
    }

    public void add(Product item){
        this.items.put(item, 0);
    }

    public void remove(String name)
    throws NoSuchProductException{
        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();

        items.remove(item);

    }

    public Product getProduct(String name){
        return items.keySet().stream().filter(c -> c.getName() == name).findFirst().orElse(null);
    }

    public int getQuantity(String name){
        return items.get(getProduct(name));
    }

    public void stock(String name, int amount)
    throws NoSuchProductException, MaxQuantityException{

        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();
        if(items.get(item) + amount > item.getMaxQuantity()) throw new MaxQuantityException();

        int count = items.get(item);
        items.put(item, count + amount);
    }

    public void sell(String name, int amount)
    throws NoSuchProductException, InsufficientQuantityException{
        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();
        if(amount > items.get(item)) throw new InsufficientQuantityException();

        int count = items.get(item);
        items.put(item, count - amount);
    }

    private class PriceComparator implements Comparator<Product>{
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getPrice() > o2.getPrice() ? -1 :(o1.getPrice() < o2.getPrice() ? 1 : 0);
        }
    }

    public List sort(){
        List result = new ArrayList<Product>(items.keySet());
        result.sort(new PriceComparator());
        return result;
    }



}
