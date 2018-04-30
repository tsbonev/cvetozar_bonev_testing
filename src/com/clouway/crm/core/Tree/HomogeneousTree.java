package com.clouway.crm.core.Tree;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomogeneousTree {


    /**
     * Compares the attributes of the leaves of a given node
     */
    /*
    public static class CompareInts implements Comparator<HomTreeElement> {
        @Override
        public int compare(HomTreeElement o1, HomTreeElement o2){
            return o1.attribute - o2.attribute;
        }
    }
    */

    private HomTreeElement parentNode;
    private HomTreeElement head;
    private boolean headFull = false;

    /**
     * Constructs a HomogenousTree class
     */
    public HomogeneousTree(){
        this.parentNode = new HomTreeElement(-1);
        head = parentNode;
    }

    /**
     * Finds the proper head node
     * @param node the node to call the recursion with
     * @return the proper head
     */
    private HomTreeElement findHead(HomTreeElement node){

        if(!node.isFull){
            return node;
        }

        for(HomTreeElement branch : node.branches){
            if(!branch.isFull){
                return branch;
            }
        }

        HomTreeElement tempBranch = new HomTreeElement(-1);
        return tempBranch; //this never executes;

    }

    /**
     * Adds a branch to the tree
     * @param attribute value of branch
     */
    public void add(int attribute){

        if(parentNode.attribute == -1){
            parentNode.attribute = attribute; //add the value of parent on first add call
        }
        else if(!this.contains(attribute)){ //check for existing value
            if(!headFull){
                headFull = head.addBranch(new HomTreeElement(attribute)); //add to head
            }
            else {
                HomTreeElement branch = new HomTreeElement(attribute); // make new branch

                head = findHead(parentNode);
                head.branches[0] = branch;
                headFull = false;
            }
        }
    }

    /**
     * Traverses the tree
     * @param node node to traverse
     * @param attribute attribute to check
     * @return result if attribute found
     */
    private boolean traverseTree(HomTreeElement node, int attribute){

        if(node.attribute == attribute) return true;

        for(HomTreeElement leaf : node.branches){
            if(leaf != null && traverseTree(leaf, attribute)){
                return true;
            }
        }

        return false;

    }

    /**
     * Starts the traverseTree recursion
     * @param attribute to search for
     * @return whether or not the attribute was found
     */
    public boolean contains (int attribute){

        return traverseTree(parentNode, attribute);

    }

    /**
     * Prints all the nodes
     * @param node the node to call the recursion with
     */
    private void preOrder(HomTreeElement node){

        System.out.println(node.attribute);

        for(HomTreeElement branch : node.branches){
            if(branch != null){
                preOrder(branch);
            }
        }
    }

    /**
     * Pritns the elements in inorder
     * @param node to call the recursion with
     */
    private void inOrder(HomTreeElement node){

        if(node == null){
            return;
        }

        inOrder(node.branches[0]);

        System.out.println(node.attribute);

        inOrder(node.branches[1]);


    }

    /**
     * Prints the nodes of a tree in postorder
     * @param node node to be checked
     */
    private void postOrder(HomTreeElement node){

        if(node == null){
            return;
        }

        postOrder(node.branches[0]);
        postOrder(node.branches[1]);

        System.out.println(node.attribute);

    }

    /**
     * Prints the elements of the tree
     * @param order in which to print
     */
    public void printElements(String order){

        if(order == "pre-order"){
            preOrder(parentNode);
        }
        else if(order == "in-order"){
            //ArrayList<Integer> result = new ArrayList<>();
            inOrder(parentNode);
            //int[] sortedResult = result.stream().mapToInt(c -> c).toArray();
            //QuickSorter sorter = new QuickSorter(sortedResult);
            //for(int element : sortedResult){
            //    System.out.println(element);
            //}
        }
        else if(order == "post-order"){

            postOrder(parentNode);

        }
    }
}
