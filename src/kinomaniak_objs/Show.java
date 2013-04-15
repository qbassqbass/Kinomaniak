/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;

/**
 *
 * @author qbass
 */
public class Show {
    public class Time{
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
}
