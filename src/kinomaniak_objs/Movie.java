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
    private String desc;
    
    public Movie(String name,String genre,String rating){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.desc = null;
    }
    public Movie(String name,String genre,String rating,String desc){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.desc = desc;
    }
    public String getName(){
        return this.name;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getRating(){
        return this.rating;
    }
    public String getDesc(){
        return this.desc;
    }
}
