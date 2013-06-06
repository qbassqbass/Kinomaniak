/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_mgmt;
import kinomaniak_objs.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qbass
 */
public class MovieDBMgmt2 {
    private String date;
    private int num;
//    private Show[] shows;
//    private Movie[] movies;
//    private CRoom[] crooms;
    private Time time;
    private List<Show> shows;
    private List<Movie> movies;
    private List<CRoom> crooms;
    private List<User> users;
//    private User[] users;
    private Show[] showtmp;
    private Movie[] movietmp;
    private CRoom[] croomtmp;
    private User[] usertmp;
    
    public MovieDBMgmt2(){        
            this.shows = new ArrayList<Show>();
            this.movies = new ArrayList<Movie>();
            this.crooms = new ArrayList<CRoom>();
            this.users = new ArrayList<User>();
    }
    public MovieDBMgmt2(String showFile,String movieFile,String croomFile,String userFile){
            this.shows = new ArrayList<Show>();
            this.movies = new ArrayList<Movie>();
            this.crooms = new ArrayList<CRoom>();
            this.users = new ArrayList<User>();
            File f = new File(showFile);
    }
    public void setTime(int hrs, int mins){
        this.time = new Time(hrs,mins);
    }
    public void getData(){
        try{
            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Shows.kin"));
            this.date = (String)we.readObject();
//            this.num = (Integer)we.readObject();
            //this.shows = (ArrayList<Show>)Arrays.asList((Show[])we.readObject()); -- if Arrays are in files
            this.shows = (ArrayList<Show>)we.readObject();
            we.close();
            we = new ObjectInputStream(new FileInputStream("Movies.kin"));
            this.movies = (ArrayList<Movie>)we.readObject();
            we.close();
            we = new ObjectInputStream(new FileInputStream("CRooms.kin"));
            this.crooms = (ArrayList<CRoom>)we.readObject();
            we.close();
            we = new ObjectInputStream(new FileInputStream("Users.kin"));
            this.users = (ArrayList<User>)we.readObject();
            we.close();
         }catch(IOException e){
            System.err.println("IO Error: "+e);
        }catch(ClassNotFoundException e){
            System.err.println("Class not found: "+e);
        }
    }
    public void saveData(){
        try{
            ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Movies.kin"));
            wy.writeObject(this.movies);
            wy.close();
            wy = new ObjectOutputStream(new FileOutputStream("CRooms.kin"));
            wy.writeObject(this.crooms);
            wy.close();
            wy = new ObjectOutputStream(new FileOutputStream("Shows.kin"));
            Date dateNow = new Date (); 
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            StringBuilder sdt = new StringBuilder( dt.format( dateNow ) );
            String st = sdt.toString();
            wy.writeObject(st);
//            wy.writeObject(this.shows.length);
            wy.writeObject(this.shows);
            wy.close();
            wy = new ObjectOutputStream(new FileOutputStream("Users.kin"));
            wy.writeObject(this.users);
            wy.close();
        }catch(IOException e){
            System.err.println("IO Error: "+e);
        }
    }
    public void addMovie(String name,String genre,String rating){        
        this.movies.add(new Movie(name,genre,rating));
    }
    public void addCRoom(int id){
        this.crooms.add(new CRoom(id));
    }
    public void addShow(Movie movie, Time time, CRoom croom ){
        int prevID;
        if(this.shows.size() == 0) prevID = 0;
        else prevID = this.shows.get(this.shows.size()-1).getID();
        this.shows.add(new Show(movie,croom,time));
        this.shows.get(this.shows.size()-1).setID(prevID+1);
    }    
    public void addUser(String name, String password,int utype){
        this.users.add(new User(name,password,utype));
    }
    public int delMovie(int n){
        if(n>=this.movies.size()) return -1;
        this.movies.remove(n);
        return 0;
    }
    public int delCRoom(int n){
        if(n>=this.crooms.size()) return -1;
        this.crooms.remove(n);
        return 0;
    }
    public int delShow(int n){
        if(n>=this.shows.size()) return -1;
        this.shows.remove(n);
        return 0;
    }
    public int delUser(int n){
        if(n>=this.users.size()) return -1;
        this.users.remove(n);
        return 0;
    }
    public Movie[] getMovies(){
        //Integer[] ints = list.toArray(new Integer[]{});
        Movie[] movs = this.movies.toArray(new Movie[]{});
        return movs;
    }
    public CRoom[] getCRooms(){
        CRoom[] crs = this.crooms.toArray(new CRoom[]{});
        return crs;
    }
    public Show[] getShows(){
        Show[] shs = this.shows.toArray(new Show[]{});
        return shs;
    }
    public User[] getUsers(){
        User[] usrs = this.users.toArray(new User[]{});
        return usrs;
    }
    public void listMovies(){
        for(int i=0;i<this.movies.size();i++){
            System.out.println(i+". "+this.movies.get(i).getName()+" Gen:"+this.movies.get(i).getGenre()+" Rat:"+this.movies.get(i).getRating());
        }
    }
    public void listCRooms(){
        for(int i=0;i<this.crooms.size();i++){
            System.out.println(i+". "+this.crooms.get(i).getID());
        }
    }
    public void listShows(){
        for(int i=0;i<this.shows.size();i++){
            System.out.println(i+". "+this.shows.get(i).getMovie().getName()+" Room: "+this.shows.get(i).getRoom().getID()+" Time: "+this.shows.get(i).getCTime().getHour()+':'+this.shows.get(i).getCTime().getMinute());
        }
    }
    public void listUsers(){
        for(int i=0;i<this.users.size();i++){
            System.out.println(i+". "+this.users.get(i).getName()+"  Type: "+this.users.get(i).getUType());
        }
    }
    public Time getTime(){
        return time;
    }
}
