package com.clouway.crm.core.Tree;

public class HeterogeneousTree {

    private HetTreeElement parentNode;
    private HetTreeElement head;
    private boolean headFull = false;
    private int elemCount;

    /**
     * Constructs a HeterogenousTree class
     */
    public HeterogeneousTree(){
        this.parentNode = new HetTreeElement(null, ++elemCount);
        head = parentNode;
    }

    /**
     * Finds the proper head node
     * @param node the node to call the recursion with
     * @return the proper head
     */
    private HetTreeElement findHead(HetTreeElement node){

        if(!node.isFull){
            return node;
        }

        if(!node.left.isFull){
            return node.left;
        }
        if (!node.right.isFull){
            return node.right;
        }

        HetTreeElement tempBranch = new HetTreeElement(-1, ++elemCount);
        return tempBranch; //this never executes;

    }


    /**
     * Adds a branch to the tree
     * @param object to add
     */
    public void add(Object object){

        if(parentNode.node == null){
            parentNode.node = object; //add the node of parent on first add call
            return;
        }

        if(!headFull){
            headFull = head.addElem(object, ++elemCount); //add to head
        }
        else {
            head = findHead(parentNode);
            head.addElem(object, ++elemCount);
            headFull = false;
        }
    }

    /**
     * Prints all the nodes
     * @param node the node to call the recursion with
     */
    private void preOrder(HetTreeElement node){

        if(node == null){
            return;
        }

        System.out.println(node.node.getClass().getSimpleName() + " " + node.index);

        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * Prints the nodes of a tree in inorder
     * @param node to call the recursion with
     */
    private void inOrder(HetTreeElement node){

        if(node == null){
            return;
        }

        inOrder(node.left);

        System.out.println(node.node.getClass().getSimpleName() + " " + node.index);

        inOrder(node.right);


    }

    /**
     * Prints the nodes of a tree in postorder
     * @param node node to be checked
     */
    private void postOrder(HetTreeElement node){

        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);

        System.out.println(node.node.getClass().getSimpleName() + " " + node.index);


    }


    /**
     * Prints the elements of the tree
     * @param order in which to print
     */
    public void printElements(String order){

        if(order.equals("pre-order")){
            preOrder(parentNode);
        }
        else if(order.equals("in-order")){
            //ArrayList<Integer> result = new ArrayList<>();
            inOrder(parentNode);
            //int[] sortedResult = result.stream().mapToInt(c -> c).toArray();
            //QuickSorter sorter = new QuickSorter(sortedResult);
            //for(int element : sortedResult){
            //    System.out.println(element);
            //}
        }
        else if(order.equals("post-order")){

            postOrder(parentNode);

        }
    }


}
