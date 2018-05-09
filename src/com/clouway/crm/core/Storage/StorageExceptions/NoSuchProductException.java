package com.clouway.crm.core.Storage.StorageExceptions;

public class NoSuchProductException extends Exception {

    public NoSuchProductException(){
        super("No such item exists in storage!");
    }

    public NoSuchProductException(String message){
        super(message);
    }
}
