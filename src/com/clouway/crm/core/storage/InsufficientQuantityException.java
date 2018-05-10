package com.clouway.crm.core.storage;

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException(){
        super("Insufficient quantity!");
    }

    public InsufficientQuantityException(String message){
        super(message);
    }
}
