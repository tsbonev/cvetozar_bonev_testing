package com.clouway.crm.core.Storage;

import com.clouway.crm.core.Storage.StorageExceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    Storage storage;

    @BeforeEach
    void setup(){
        storage = new Storage();
        storage.add(new Product("bread", 2));
    }

    @Test
    void addProduct() {
        storage.add(new Product("milk", 3));

        assertEquals(2, storage.getSize());
    }

    @Test
    void stockBread()
    throws NoSuchProductException{
        storage.stock("bread", 6);
        assertEquals(6, storage.getQuantity("bread"));
    }

    @Test
    void stockNonExistentProduct(){
        assertThrows(NoSuchProductException.class, () -> storage.stock("madeupname", 6));
    }

    @Test
    void sellBread()
    throws InsufficientQuantityException, NoSuchProductException{
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


}