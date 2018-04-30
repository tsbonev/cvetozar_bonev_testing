package com.clouway.crm.core.Cars;

import java.time.LocalDateTime;

public class VW extends Automobile{

    public VW(double maxSpeed, String color, LocalDateTime productionDate){
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.productionDate = productionDate;
    }

}
