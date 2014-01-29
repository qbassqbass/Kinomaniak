/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_objs;

import java.io.Serializable;

/**
 *
 * @author Qbass
 */
public class Attraction implements Serializable{
    private int id;
    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
    
    public Attraction(String name, float price){
        this.name = name;
        this.price = price;
        this.id = this.getLastId() + 1;
    }
    
    public Attraction(int id, String name, float price){
        this.name = name;
        this.price = price;
        this.id = id;
        
    }
    
    private int getLastId(){
        int tmp = -1;
        
        return tmp;
    }
}
