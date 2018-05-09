package com.clouway.crm.core.Storage.StorageExceptions;

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException(){
        super("Insufficient quantity!");
    }

    public InsufficientQuantityException(String message){
        super(message);
    }
}
