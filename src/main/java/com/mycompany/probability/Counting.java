
package com.mycompany.probability;

import java.util.ArrayList;
import java.util.Collections;


public class Counting {
    
    private Box box;
    private ArrayList<OneColorBalls> expected;
    private int tryNumber;
    
    public Counting(Box box, ArrayList<OneColorBalls> exp, int number) {
        this.box = box;
        this.expected = exp;
        this.tryNumber = number;
    }
    
    public double count() {
        
        int passed = 0;
        
        for(int i = 0; i < 1000000; i++) {
            ArrayList<String> picked = new ArrayList<>();
            int count = 0;

            try {
                Box copyBox = (Box) box.clone();
                
                ArrayList<String> copyArray = new ArrayList<>();
                for(String string: copyBox.getBox()) {
                    copyArray.add(string);                    
                }
                copyBox.setBox(copyArray);

                for(int j = 0; j < tryNumber; j++) {  
                    picked.add(copyBox.drawBall());
                }

                for(OneColorBalls balls: expected) {
                    String color = balls.getColor();
                    int howMany = balls.getNumber();
                    
                    if(howMany <= Collections.frequency(picked, color)) {
                        count++;    
                    }                    
                }

                if(count == expected.size()) {
                    passed++;
                } 
                
            } catch (CloneNotSupportedException c){
                System.out.println(c);
            } 
        }

        return (double)passed/1000000 * 100;
    } 
}
