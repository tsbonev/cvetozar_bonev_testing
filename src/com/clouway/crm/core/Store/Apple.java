package com.clouway.crm.core.Store;

public class Apple extends Food {

    private enum Color {
        RED, GREEN;
    }

    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

}
