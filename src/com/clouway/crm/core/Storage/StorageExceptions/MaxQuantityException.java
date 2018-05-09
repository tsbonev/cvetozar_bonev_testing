package com.clouway.crm.core.Storage.StorageExceptions;

public class MaxQuantityException extends Exception {

    public MaxQuantityException(){
        super("Max quantity has been surpassed!");
    }

    public MaxQuantityException(String message){
        super(message);
    }

}
