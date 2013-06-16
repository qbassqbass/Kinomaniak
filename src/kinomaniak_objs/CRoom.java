/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

import java.io.Serializable;

/**
 * Klasa reprezentująca salę kinową
 * @author qbass
 */
public class CRoom implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private boolean[][] seats;
    
    /**
     * Konstruktor klasy Sali kinowej
     * @param id identyfikator sali kinowej
     */
    public CRoom(int id){
        this.id = id;
        seats = new boolean[10][10];
    }
    /**
     * Metoda zwracająca ID sali kinowej
     * @return identyfikator sali kinowej
     */
    public int getID(){
        return this.id;
    }
}
