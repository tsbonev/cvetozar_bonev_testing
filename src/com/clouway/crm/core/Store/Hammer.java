package com.clouway.crm.core.Store;

public class Hammer extends Appliance {

    private enum Color {
        RED, BLACK, WHITE;
    }

    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

}
