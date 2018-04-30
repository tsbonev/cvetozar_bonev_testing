package com.clouway.crm.core.Cars;

import java.time.LocalDateTime;

public class Mercedes extends Automobile {

    public Mercedes(double maxSpeed, String color, LocalDateTime productionDate){
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.productionDate = productionDate;
    }

}
