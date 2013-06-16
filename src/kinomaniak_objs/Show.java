/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

import java.io.Serializable;

/**
 * Klasa reprezentująca dany seans
 * @author qbass
 */
public class Show implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private int showid;
    private Movie mov;
    private CRoom room;
    private Time time;
    
    /**
     * Konstruktor seansu
     * @param mov obiekt filmu Movie
     * @param room obiekt sali kinowej CRoom
     * @param time obiekt czasu Time
     */
    public Show(Movie mov, CRoom room, Time time){
        
        
        this.mov = mov;
        this.room = room;
        this.time = time;
    }
    /**
     * Metoda zwracająca czas rozpoczęcia seansu
     * @return tablica czasu, gdzie int[0] - godzina, int[1] - minuta
     */
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
    /**
     * Metoda zwracająca obiekt filmu
     * @return obiekt filmu Movie
     */
    public Movie getMovie(){
        return mov;
    }
    /**
     * Metoda zwracająca obiekt sali kinowej
     * @return obiekt sali kinowej CRoom
     */
    public CRoom getRoom(){
        return room;
    }
    /**
     * Metoda zwracająca obiekt czasu
     * @return obiekt czasu Time
     */
    public Time getCTime(){
        return time;
    }
    /**
     * Metoda zwracająca identyfikator Seansu
     * @return identyfikator seansu
     */
    public int getID(){
        return this.showid;
    }
    /**
     * Metoda ustawiąjąca identyfikator Seansu
     * @param id identyfikator seansu do ustawienia
     */
    public void setID(int id){
        this.showid = id;
    }
}
