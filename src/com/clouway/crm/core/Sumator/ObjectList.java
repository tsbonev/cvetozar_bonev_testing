package com.clouway.crm.core.Sumator;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ObjectList {

    ArrayList<Object> attributes;
    final int SIZE = 1;

    public ObjectList(){
        this.attributes = new ArrayList<Object>(SIZE);
    }

    public boolean add(Object obj){
        try{
            if(attributes.size() == SIZE){
                throw new IndexOutOfBoundsException();
            }
            attributes.add(obj);
            return true;
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean remove(){
        try{
            if(attributes.size() == 0){
                throw new EmptyStackException();
            }
            attributes.remove(attributes.get(attributes.size() - 1));
            return true;
        }
        catch (EmptyStackException e){
            throw new EmptyStackException();
        }
    }

    public void printAllElements(){
        for (Object obj : attributes
             ) {
            System.out.println(obj);
        }
    }



}