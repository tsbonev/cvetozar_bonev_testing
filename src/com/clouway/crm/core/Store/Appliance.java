package com.clouway.crm.core.Store;

import org.omg.CORBA.Current;

public class Appliance extends Product implements IUseable{

    private static enum CurrentCondition {
        NEW, USED, DAMAGED, BROKEN;
    }

    private CurrentCondition condition;

    public CurrentCondition getCondition(){
        return this.condition;
    }

    public void setCondition(CurrentCondition condition){
        this.condition = condition;
    }

    public void Use(){
        System.out.println(this.getClass().getSimpleName() + " was used");
    }

}
