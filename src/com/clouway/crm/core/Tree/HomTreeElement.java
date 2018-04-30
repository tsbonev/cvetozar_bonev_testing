package com.clouway.crm.core.Tree;

public class HomTreeElement {

    public int attribute;

    public boolean isFull = false;

    public HomTreeElement[] branches = new HomTreeElement[2];

    /**
     * Constructs a HomTreeElement
     * @param attribute the value to give the element
     */
    public HomTreeElement(int attribute){
        this.attribute = attribute;
    }

    /**
     * adds a leaf ot the current node's branches
     * @param branch leaf to be added
     * @return whether or not the node has been filled with two leaves
     */
    public boolean addBranch(HomTreeElement branch){
        if(branches[0] == null){
            branches[0] = branch;
            return false; //is full = false
        }
        else if(branches[1] == null){
            branches[1] = branch;
            this.isFull = true;
            return true; //is full = true
        }
        return true; //branches were already full, this never executes
    }

}
