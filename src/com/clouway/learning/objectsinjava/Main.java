package com.clouway.learning.objectsinjava;

import com.clouway.crm.core.Tree.*;
import com.clouway.crm.core.Store.*;
import com.clouway.crm.core.Sumator.*;

public class Main {

    public static void main(String[] args) {

        HeterogeneousTree tree = new HeterogeneousTree();
        tree.add(new Worker());
        tree.add(new Clerk());
        tree.add(2);
        tree.add("Check");
        tree.add('C');

        tree.printElements("pre-order");
        System.out.println("---");
        tree.printElements("in-order");
        System.out.println("---");
        tree.printElements("post-order");



        Sumator sumator = new Sumator();

        System.out.println(sumator.sum("2131", "1341412"));
        System.out.println(sumator.sum("21gr2", "412g")); //NumberFormat
        System.out.println(sumator.sum("213176756435364737", "1341412125454634636774623")); //NumberFormat
        System.out.println("-----------");




    }
}
