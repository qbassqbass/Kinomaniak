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
public class Show implements Serializable {
    public class Time implements Serializable{
        private int hour;
        private int minute;
        
        public Time(int hour,int minute){
            this.hour = hour;
            this.minute = minute;
        }        
        public int getHour(){
            return this.hour;
        }
        public int getMinute(){
            return this.minute;
        }
    }
    private Movie mov;
    private CRoom room;
    private Time time;
    
    public Show(Movie mov, CRoom room, Time time){
        this.mov = mov;
        this.room = room;
        this.time = time;
    }
    public int[] getTime(){
        int tim[] = new int[2];
        tim[0] = this.time.getHour();
        tim[1] = this.time.getMinute();
        return tim;
    }
}
