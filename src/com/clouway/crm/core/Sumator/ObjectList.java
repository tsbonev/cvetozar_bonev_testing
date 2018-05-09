package com.clouway.crm.core.Sumator;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ObjectList {

    List<Object> attributes;
    final int SIZE;

    public ObjectList(int size){
        this.SIZE = size;
        this.attributes = new ArrayList<>(SIZE);
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
