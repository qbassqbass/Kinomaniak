/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_database;

import kinomaniak_objs.Movie;

/**
 *
 * @author Qbass
 */
public class Parser {
    public String createSaveQuery(Movie mov){
        String query = "";
        
        query = "INSERT INTO movie VALUES (NULL, '" + mov.getName() +"', '" + mov.getGenre() + "', '" + mov.getRating() + "', '" + mov.getDesc() + "');";
        return query;
    }
}
