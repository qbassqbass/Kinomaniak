/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import kinomaniak_objs.Movie;

/**
 *
 * @author Qbass
 */
public class DBConnector {
    private final static String DBURL = "jdbc:mysql://192.168.1.2:3306/kinomaniak?characterEncoding=utf8";
    private final static String DBUSER = "kinomaniak";
    private final static String DBPASS = "123";
    private final static String DBDRIVER = "com.mysql.jdbc.Driver";
    
    private Connection connection;
    private Statement statement;
    private String query;
    private final Parser parser;

    public DBConnector(){
        this.parser = new Parser();
    }
    
    public void save(Movie mov){
        query = this.parser.save(mov);
        
        try{
            Class.forName(DBDRIVER).newInstance();
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            connection.close();
        }catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e){
            System.err.println("Exception: "+e);
        }
    }
}
