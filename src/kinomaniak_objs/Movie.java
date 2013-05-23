/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

import java.io.Serializable;

/**
 *
 * @author qbass
 */
public class Movie implements Serializable{
    
    private String name;
    private String genre;
    private String rating;
    
    public Movie(String name,String genre,String rating){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }
//    public int addTimes(Time time){
//        if(this.times.length<10){
//            this.times[this.times.length+1] = time;
//            return 0;
//        }else return -1;
//    }
//    public int addTimes(int hour,int minute){
//        if(this.times.length<10){
//            this.times[this.times.length+1] = new Time(hour,minute);
//            return 0;
//        }else return -1;
//    }
//    public int delTimes(int id){
//        
//        return -1;
//    }
    public String getName(){
        return this.name;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getRating(){
        return this.rating;
    }
//    public Time[] getTimes(){
//        return this.times;
//    }
//    public int[] getTime(int id){
//        int tim[] = new int[2];
//        tim[0] = this.getTimes()[id].getHour();
//        tim[1] = this.getTimes()[id].getMinute();
//        return tim;
//    }
}
