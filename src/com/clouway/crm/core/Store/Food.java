package com.clouway.crm.core.Store;

import java.time.LocalDateTime;

public class Food extends Product implements IEdible{

    private LocalDateTime ExpirationDate;

    public LocalDateTime getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(LocalDateTime date){
        this.ExpirationDate = date;
    }

    public void Eat(){
        this.setQuantity(-1);
    }
}
