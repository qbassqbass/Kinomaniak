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
public class Ticket {
    private int id;
    private int type;
    
    public Ticket(int type){
        this.id = this.getLastId() + 1;
        this.type = type;
    }
    
    private int getLastId(){
        int tmp = -1;
        
        return tmp;
    }
}
