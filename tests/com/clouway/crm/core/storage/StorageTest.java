package com.clouway.crm.core.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.common.collect.Lists;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    Storage storage;

    @BeforeEach
    void setup(){
        storage = new Storage();
    }

    @Test
    void addProduct()
    throws DuplicateProductException{
        storage.addStock(new Product("milk", 3, 2));

        assertTrue(storage.containsProduct("milk"));
    }

    @Test
    void addTwoIdenticalProducts()
    throws DuplicateProductException{
        Product milk1 = new Product("milk", 3, 2);
        Product milk2 = new Product("milk", 3, 2);
        storage.addStock(milk1);

        assertThrows(DuplicateProductException.class, () -> storage.addStock(milk2));

    }

    @Test
    void removeProduct()
    throws NoSuchProductException, DuplicateProductException{
        storage.addStock(new Product("generic bread", 1, 2));
        storage.removeProduct("generic bread");

        assertFalse(storage.containsProduct("generic bread"));
    }

    @Test
    void removeNonExistingProduct(){
        assertThrows(NoSuchProductException.class,
                () -> storage.removeProduct("madeupname"));
    }

    @Test
    void stockBread()
    throws NoSuchProductException, MaxQuantityException, DuplicateProductException{
        storage.addStock(new Product("bread", 6, 1));
        storage.stockProduct("bread", 6);
        assertThat(storage.getQuantity("bread"), is(6));
    }

    @Test
    void stockNonExistentProduct(){
        assertThrows(NoSuchProductException.class,
                () -> storage.stockProduct("madeupname", 6));
    }

    @Test
    void stockMoreThanAllowed()
    throws DuplicateProductException{
        storage.addStock(new Product("bread", 1, 1));
        assertThrows(MaxQuantityException.class,
                () -> storage.stockProduct("bread", 100));
    }

    @Test
    void sellBread()
    throws InsufficientQuantityException, NoSuchProductException, MaxQuantityException, DuplicateProductException{
        storage.addStock(new Product("bread", 1, 1));
        storage.stockProduct("bread", 1);
        storage.sellProduct("bread", 1);

        assertThat(storage.getQuantity("bread"), is(0));
    }

    @Test
    void sellNonExistentProduct(){
        assertThrows(NoSuchProductException.class,
                () -> storage.sellProduct("madeupname", 1));
    }

    @Test
    void sellMoreThanIsPresent()
    throws DuplicateProductException{
        storage.addStock(new Product("bread", 1, 1));
        assertThrows(InsufficientQuantityException.class,
                () -> storage.sellProduct("bread", 1));
    }

    @Test
    void printProductsByPrice()
    throws DuplicateProductException{

        Product bread = new Product("bread", 6,1);
        Product white_bread = new Product("white bread", 6,2);
        Product luxury_bread = new Product("luxury bread", 6,100);

        storage.addStock(bread);
        storage.addStock(white_bread);
        storage.addStock(luxury_bread);

        assertThat(storage.sortByPrice(), is(equalTo(
                Lists.newArrayList(
                        luxury_bread,
                        white_bread,
                        bread
                )
        )));

    }

    @Test
    void addNullableProduct(){
        assertThrows(NullPointerException.class,
                () -> storage.addStock(null));
    }


}