package com.clouway.crm.core.storage;

import java.util.*;

public class Storage {

    private Map<Product, Integer> sInventory;

    /**
     * Constructs a storage class.
     */
    public Storage(){
        this.sInventory = new HashMap<>();
    }

    /**
     * Checks the inventory for a specified by name product
     *
     * @return whether or not the product exists
     */
    public boolean containsProduct(String item){
        return sInventory.containsKey(getProduct(item));
    }

    /**
     * Adds a product as a key into the map.
     *
     * @param item product to addStock as a key
     */
    public void addStock(Product item)
    throws NullPointerException, DuplicateProductException{
        if(item == null) throw new NullPointerException();

        //TODO: Make the addStock create its Product internally
        //TODO: Change how orders are made
        //TODO: Maybe combine integer quantity and Product but that would loose efficiency at a load of small sales

        if(sInventory.keySet().stream()
                .filter(product -> product.getName() == item.getName()).count() > 0){
            throw new DuplicateProductException();
        }

        this.sInventory.put(item, 0);
    }

    /**
     * Removes a given product from the storage by name.
     *
     * @param name of the product to removeProduct
     * @throws NoSuchProductException exception if product does not exist
     */
    public void removeProduct(String name)
    throws NoSuchProductException{
        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();

        sInventory.remove(item);

    }

    /**
     * Returns the quantity of a given product.
     *
     * @param name of the product
     * @return the current quantity of the product
     */
    public int getQuantity(String name){
        return sInventory.get(getProduct(name));
    }

    /**
     * Increases the quantity of a given product.
     *
     * @param name of the product
     * @param amount that should be stocked
     * @throws NoSuchProductException exception if the product does not exist
     * @throws MaxQuantityException exception if the product does not allow the sought quantity increase
     */
    public void stockProduct(String name, int amount)
    throws NoSuchProductException, MaxQuantityException{

        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();
        if(sInventory.get(item) + amount > item.getMaxQuantity()) throw new MaxQuantityException();

        int count = sInventory.get(item);
        sInventory.put(item, count + amount);
    }

    /**
     * Sells a product from the storage.
     *
     * @param name of the product to be sold
     * @param amount that should be sold
     * @throws NoSuchProductException exception if product does not exist
     * @throws InsufficientQuantityException exception if there is not enough of the product
     */
    public void sellProduct(String name, int amount)
    throws NoSuchProductException, InsufficientQuantityException{
        Product item = getProduct(name);
        if(item == null) throw new NoSuchProductException();
        if(amount > sInventory.get(item)) throw new InsufficientQuantityException();

        int count = sInventory.get(item);
        sInventory.put(item, count - amount);
    }

    /**
     * Sorts the keys of the map by price in descending order.
     * @return a sorted list of the keys
     */
    public List<Product> sortByPrice(){
        List<Product> result = new ArrayList<>(sInventory.keySet());
        result.sort(new PriceComparator());
        return result;
    }

    /**
     * Returns a product key by a given name.
     *
     * @param name of the product that is sought.
     * @return the product or null if it is not found
     */
    private Product getProduct(String name){
        return sInventory.keySet().stream().filter(c -> c.getName() == name).findFirst().orElse(null);
    }

    /**
     * Comparator that sorts by price in descending order.
     */
    private class PriceComparator implements Comparator<Product>{
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getPrice() > o2.getPrice() ? -1 :(o1.getPrice() < o2.getPrice() ? 1 : 0);
        }
    }



}
