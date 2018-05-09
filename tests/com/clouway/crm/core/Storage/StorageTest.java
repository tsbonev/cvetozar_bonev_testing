package com.clouway.crm.core.Storage;

import com.clouway.crm.core.Storage.StorageExceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    Storage storage;

    @BeforeEach
    void setup(){
        storage = new Storage();

        Product bread = new Product("bread", 6,1);
        Product white_bread = new Product("white bread", 6,2);
        Product luxury_bread = new Product("luxury bread", 6,100);

        storage.add(bread);
        storage.add(white_bread);
        storage.add(luxury_bread);

    }

    @Test
    void addProduct() {
        storage.add(new Product("milk", 3, 2));

        assertEquals(4, storage.getSize());
    }

    @Test
    void removeProduct()
    throws NoSuchProductException{
        storage.remove("white bread");

        assertEquals(2, storage.getSize());
    }

    @Test
    void removeNonExistingProduct()
    throws NoSuchProductException{
        assertThrows(NoSuchProductException.class, () -> storage.remove("madeupname"));
    }

    @Test
    void stockBread()
    throws NoSuchProductException, MaxQuantityException{
        storage.stock("bread", 6);
        assertEquals(6, storage.getQuantity("bread"));
    }

    @Test
    void stockNonExistentProduct(){
        assertThrows(NoSuchProductException.class, () -> storage.stock("madeupname", 6));
    }

    @Test
    void stockMoreThanAllowed(){
        assertThrows(MaxQuantityException.class, () -> storage.stock("bread", 100));
    }

    @Test
    void sellBread()
    throws InsufficientQuantityException, NoSuchProductException, MaxQuantityException{
        storage.stock("bread", 1);
        storage.sell("bread", 1);
        assertEquals(0, storage.getQuantity("bread"));
    }

    @Test
    void sellNonExistentProduct(){
        assertThrows(NoSuchProductException.class, () -> storage.sell("madeupname", 1));
    }

    @Test
    void sellMoreThanIsPresent(){
        assertThrows(InsufficientQuantityException.class, () -> storage.sell("bread", 1));
    }

    @Test
    void printProductsByPrice(){

        List<Product> sortedList = new ArrayList<>();
        sortedList.add(storage.getProduct("luxury bread"));
        sortedList.add(storage.getProduct("white bread"));
        sortedList.add(storage.getProduct("bread"));

        assertTrue(sortedList.equals(storage.sort()));

    }


}