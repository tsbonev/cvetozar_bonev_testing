package com.clouway.crm.core.storage;

public class DuplicateProductException extends Exception{

    public DuplicateProductException(){
        super("Product already in storage!");
    }

    public DuplicateProductException(String message){
        super(message);
    }

}
