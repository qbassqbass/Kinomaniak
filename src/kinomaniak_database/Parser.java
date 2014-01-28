/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_database;

import kinomaniak_objs.*;

/**
 *
 * @author Qbass
 */
public class Parser {
    
    public String save(Object obj){
        String query = "";
        if(obj instanceof Movie){
            Movie mov = (Movie) obj;
            query = "INSERT INTO movie VALUES (NULL, '" + mov.getName() +"', '" + mov.getGenre() + "', '" + mov.getRating() + "', '" + mov.getDesc() + "');";
        }else if(obj instanceof CRoom){
            CRoom cr = (CRoom) obj;
            
        }else if(obj instanceof Attraction){
            Attraction at = (Attraction) obj;
            
        }else if(obj instanceof Product){
            Product pr = (Product) obj;
            
        }else if(obj instanceof Report){
            Report rep = (Report) obj;
            
        }else if(obj instanceof Res){
            Res res = (Res) obj;
            
        }else if(obj instanceof Show){
            Show sh = (Show) obj;
            
        }else if(obj instanceof Ticket){
            Ticket tick = (Ticket) obj;
            
        }else if(obj instanceof Time){
            Time tim = (Time) obj;
            
        }else if(obj instanceof User){
            User usr = (User) obj;
            
        }else if(obj instanceof GoldCard){
            GoldCard gc = (GoldCard) obj;
            
        }
        return query;
    }
    
    public String saveMovie(Movie mov){
        String query = "";
        
        query = "INSERT INTO movie VALUES (NULL, '" + mov.getName() +"', '" + mov.getGenre() + "', '" + mov.getRating() + "', '" + mov.getDesc() + "');";
        return query;
    }
    
    public String saveCRoom(CRoom cr){
        String query = "";
        
        return query;
    }
}
