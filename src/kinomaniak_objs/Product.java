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
public class Product {
    private int id;
    private String name;
    private int type;
    private float price;
    
    public Product(String name, int type, float price){
        this.name = name;
        this.type = type;
        this.price = price;
        this.id = this.getLastId() + 1;
    }
    
    private int getLastId(){
        int tmp = -1;
        
        return tmp;
    }
}
