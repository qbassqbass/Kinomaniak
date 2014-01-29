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
public class AttrRes implements Serializable{
    private String imienazwisko;
    private int atrid;
    private Time time;
    
    public AttrRes(String name, int id, Time time){
        this.imienazwisko = name;
        this.atrid = id;
        this.time = time;
    }
    
    public String getName(){
        return this.imienazwisko;
    }
    
    public int getAttrId(){
        return this.atrid;
    }
    
    public Time getCTime(){
        return this.time;
    }
    
    public int[] getTime(){
        int tim[] = new int[2];
        tim[0] = this.time.getHour();
        tim[1] = this.time.getMinute();
        return tim;
    }
    /**
     * Metoda zwracająca datę rozpoczęcia seansu
     * @return tablica daty, gdzie int[0] - dzień, int[1] - miesiąc, int[2] - rok
     */
    public int[] getDate(){
        int dt[] = new int[3];
        dt[0] = this.time.getDay();
        dt[1] = this.time.getMonth();
        dt[2] = this.time.getYear();
        return dt;
    }
    
    /**
     * Metoda zwracająca sformatowaną datę
     * @return String ze sformatowaną datą w postaci dzień/miesiąc/rok
     */
    public String getFormattedDate(){
        String tmp = this.getDate()[0]+"/"+this.getDate()[1]+"/"+this.getDate()[2];
        return tmp;
    }
    /**
     * Metoda zwracająca sformatowany czas
     * @return String ze sformatowanym czasem w postaci godzina:minuta
     */
    public String getFormattedTime(){
        String tmp = this.getTime()[0]+":"+this.getTime()[1];
        return tmp;
    }
    /**
     * Metoda zwracająca sformatowaną datę/czas
     * @return String w postaci dzień/miesiąc/rok godzina:minuta
     */
    public String getFormatted(){
        String tmp = this.getFormattedDate()+" "+this.getFormattedTime();
        return tmp;
    }
}
