package com.clouway.crm.core.Tree;

public class HetTreeElement<T> {

    T node;
    HetTreeElement left;
    HetTreeElement right;
    int index;
    boolean isFull = false;

    public HetTreeElement(T object, int elemCount){
        this.node = object;
        this.index = elemCount;
        elemCount++;
    }

    public boolean addElem (Object object, int elemCount){
        if(left == null){
            this.left = new HetTreeElement(object, elemCount);
            return false;
        }
        else if(right == null){
            this.right = new HetTreeElement(object, elemCount);
            this.isFull = true;
            return true;
        }
        return true;
    }

}
