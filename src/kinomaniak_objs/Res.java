/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import org.jdom2.Element;

/**
 * Klasa reprezentująca rezerwację seansu
 * @author qbass
 */
public class Res implements Serializable{
    
    private static final long serialVersionUID = 4L;
   
    private String imienazwisko;
    private int showid;
    private int[][] seat;
    private boolean checked; // potwierdzenie rezerwacji
    private boolean ok; // odebrana rezerwacja
    
    public Element toXML(){
        Element res = new Element("Res");
        res.addContent(new Element("imienazwisko").setText(String.valueOf(this.imienazwisko)));
        res.addContent(new Element("showid").setText(String.valueOf(this.showid)));
        res.addContent(new Element("checked").setText(String.valueOf(this.checked)));
        res.addContent(new Element("ok").setText(String.valueOf(this.ok)));
        Element seats = new Element("seats");
        seats.setAttribute("count", String.valueOf(this.seat.length));
        for (int[] s : this.seat) {
            seats.addContent(new Element("row").setText(String.valueOf(s[0])));
            seats.addContent(new Element("col").setText(String.valueOf(s[1])));
        }
        res.addContent(seats);
        return res;
    }
    
    public Res(Element node){
        if(!node.getName().equals("Res")){
//            throw new RuntimeException("Wrong element type");
            System.out.println("Wrong element type: Res, got: "+node.getName());
        }
        
        this.imienazwisko = node.getChildText("imienazwisko");
        this.showid = Integer.valueOf(node.getChildText("showid"));
        this.checked = Boolean.valueOf(node.getChildText("checked"));
        Element ss = node.getChild("seats");
        int c = Integer.valueOf(ss.getAttribute("count").getValue());
        this.seat = new int[c][2];
        for(int i = 0; i < c; i++){
//            this.seat[i][0] = 
        }
    }
    
    /**
     * Konstruktor klasy Rezerwacji danego seansu dla pojedyńczego miejsca
     * @param nazwa nazwa/nazwisko klienta, który rezerwuje dany seans
     * @param id identyfikator rezerwowanego seansu
     * @param seat tablica dwóch liczb identyfikujących rząd oraz miejsce w danym rzędzie do zarezerwowania
     */
    public Res(String nazwa,int id, int[] seat){
        this.seat = new int[1][2];
        this.imienazwisko = nazwa;
        this.showid = id;
        this.seat[0] = seat;
        this.checked = false;
        this.ok = false;
    }
    /**
     * Konstruktor klasy Rezerwacji danego seansu dla kilku miejsc
     * @param nazwa nazwa/nazwisko klienta, który rezerwuje dany seans
     * @param id identyfikator rezerwowanego seansu
     * @param ilosc ilość miejsc do rezerwacji
     * @param seat tablica dwóch liczb identyfikujących rząd oraz miejsce w danym rzędzie do zarezerwowania dla każdego z 'ilosc' miejsc
     */
    public Res(String nazwa,int id,int ilosc, int[][] seat){
        this.seat = new int[ilosc][2];
        this.imienazwisko = nazwa;
        this.showid = id;
        this.seat = seat;
        this.checked = false;
        this.ok = false;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            Res res = (Res) obj;
            if ((res.imienazwisko).equals(this.imienazwisko) && Arrays.deepEquals(this.seat, res.seat) && res.showid == this.showid) {
                System.out.println("equals");
                isEqual = true;
            }
        } 
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.imienazwisko);
        hash = 59 * hash + this.showid;
        hash = 59 * hash + Arrays.hashCode(this.seat);
        return hash;
    }

    /**
     * akceptacja rezerwacji
     */
    public void accept(){
        this.checked = true;
    }
    /**
     * sprawdzenie czy dana rezerwacja została potwierdzona przez użytkownika
     * @return true jeśli potwierdzona
     */
    public boolean ischecked(){
        return this.checked;
    }
    /**
     * odbiór rezerwacji
     */
    public void get(){
        this.ok = true;
    }
    /**
     * sprawdzenie czy rezerwacja jest już odebrana
     * @return true jeśli rezerwacja została odebrana
     */
    public boolean isok(){
        return this.ok;
    }
    /**
     * Metoda zwracająca nazwę/nazwisko osoby rezerwującej dany seans
     * @return nazwa/nazwisko rezerwującego
     */
    public String getName(){
        return this.imienazwisko;
    }
    /**
     * Metoda zwracająca identyfikator zarezerwowanego seansu
     * @return id seansu
     */
    public int getShowID(){
        return this.showid;
    }
    /**
     * Metoda zwracająca tablicę identyfikującą dokładne miejsca rezerwacji
     * @return int[][] 
     */
    public int[][] getSeats(){
        return this.seat;
    }
    public String formatSeats(){
        String tmp = "";
        for(int s[] : this.getSeats()){
            tmp += "Rząd: "+s[0]+" Miejsce: "+s[1]+"\n";
        }
        return tmp;
    }
    
}
