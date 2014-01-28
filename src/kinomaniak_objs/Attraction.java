/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_objs;

/**
 *
 * @author Qbass
 */
public class Attraction {
    private int id;
    private String name;
    private float price;
    
    public Attraction(String name, float price){
        this.name = name;
        this.price = price;
        this.id = this.getLastId() + 1;
    }
    
    private int getLastId(){
        int tmp = -1;
        
        return tmp;
    }
}
