
package com.mycompany.probability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


public class Box implements Cloneable{

    private ArrayList<String> box;
    
    public Box() {
        this.box = new ArrayList<>();
    }
    
    public void addBalls(OneColorBalls balls) {      
        for(int i = 0; i < balls.getNumber(); i++) {
            this.box.add(balls.getColor());
        }
        Collections.shuffle(box);
    }
    
    public String drawBall() {
        int index = ThreadLocalRandom.current().nextInt(0, this.box.size());
        String chossen = this.box.get(index);
        this.box.remove(index);
        
        return chossen;
    }
    
    public ArrayList<String> getBox() {
        return this.box;
    }
    
    public void setBox(ArrayList<String> box) {
        this.box = box;
    }
    
    public Object clone() throws CloneNotSupportedException{  
        return super.clone();
    }  
}
