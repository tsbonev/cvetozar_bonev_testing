package com.clouway.crm.core.Cars;

import java.time.LocalDateTime;

public class Opel extends Automobile {

    public Opel(double maxSpeed, String color, LocalDateTime productionDate, int numOfPassangers, double wegith){
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.productionDate = productionDate;
        this.numOfPassanger = numOfPassangers;
        this.weight = wegith;
    }

    public double weight;
    public int numOfPassanger;



}
