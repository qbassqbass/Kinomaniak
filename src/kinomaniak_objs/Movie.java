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
    
    /**
     *
     * @param name
     * @param genre
     * @param rating
     */
    public Movie(String name,String genre,String rating){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.desc = null;
    }
    /**
     *
     * @param name
     * @param genre
     * @param rating
     * @param desc
     */
    public Movie(String name,String genre,String rating,String desc){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.desc = desc;
    }
    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }
    /**
     *
     * @return
     */
    public String getGenre(){
        return this.genre;
    }
    /**
     *
     * @return
     */
    public String getRating(){
        return this.rating;
    }
    /**
     *
     * @return
     */
    public String getDesc(){
        return this.desc;
    }
}
