
package com.mycompany.probability;


public class OneColorBalls {
    
    private String color;
    private int number;
    
    public OneColorBalls(String color, int number) {
        this.color = color;
        this.number = number;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public String getColor() {
        return this.color;
    }
    
    @Override
    public String toString() {
        return this.color + " - " + this.number;
    }
    
}
