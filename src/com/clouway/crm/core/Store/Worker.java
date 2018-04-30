package com.clouway.crm.core.Store;

public class Worker extends Person implements IWorker {

    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary += salary;
    }

    public void getPaid() {
        this.setWallet(this.getSalary());
    }

}
