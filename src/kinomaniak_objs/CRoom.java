/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

/**
 *
 * @author qbass
 */
public class CRoom {
    private int id;
    private boolean[][] seats;
    
    public CRoom(int id){
        this.id = id;
        seats = new boolean[10][10];
    }
}
