/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

import java.io.Serializable;

/**
 * Klasa reprezentująca czas rozpoczęscia seansu
 * @author qbass
 */
public class Time implements Serializable{
    private static final long serialVersionUID = 2L;
        private int hour;
        private int minute;
        private int day;
        private int month;
        private int year;
        
    /**
     * Konstruktor klasy czasu dla ustawienia godziny
     * @param hour dokładna godzina
     * @param minute dokładna minuta
     */
    public Time(int hour,int minute){
            this.hour = hour;
            this.minute = minute;
        }        
    /**
     * Konstruktor klasy czasu dla ustawienia daty i godziny
     * @param hour godzina
     * @param minute minuta
     * @param day dzień
     * @param month miesiąc
     * @param year rok
     */
    public Time(int hour,int minute,int day,int month,int year){
        this.hour = hour;
        this.minute  =minute;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    /**
     * Pobiera godzinę z klasy {@link #Time}.
     * @return hour godzina rozpoczęcia
     */
    public int getHour(){
            return this.hour;
        }
    /**
     * Pobiera minutę z klasy {@link #Time}.
     * @return minute minuta rozpoczęcia
     */
    public int getMinute(){
            return this.minute;
        }
    /**
     * Pobiera dzień z klasy  {@link #Time}.
     * @return day
     */
    public int getDay(){
        return this.day;
    }
    /**
     * Pobiera miesiąc z klasy {@link #Time}.
     * @return month
     */
    public int getMonth(){
        return this.month;
    }
    /**
     * Pobiera rok z klasy  {@link #Time}.
     * @return year
     */
    public int getYear(){
        return this.year;
    }
    }
