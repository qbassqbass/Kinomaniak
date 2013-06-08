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
    private Time time;
    private List<Show> shows;
    private List<Movie> movies;
    private List<CRoom> crooms;
    private List<User> users;
    private List<Res> ress;
    
    /**
     *
     */
    public MovieDBMgmt2(){        
            this.shows = new ArrayList<Show>();
            this.movies = new ArrayList<Movie>();
            this.crooms = new ArrayList<CRoom>();
            this.users = new ArrayList<User>();
    }
    /**
     *
     * @param showFile
     * @param movieFile
     * @param croomFile
     * @param userFile
     */
    public MovieDBMgmt2(String showFile,String movieFile,String croomFile,String userFile){
            this.shows = new ArrayList<Show>();
            this.movies = new ArrayList<Movie>();
            this.crooms = new ArrayList<CRoom>();
            this.users = new ArrayList<User>();
            File f = new File(showFile);
    }
    /**
     * Metoda ustawiająca czas dla danego seansu
     * @param hrs godzina
     * @param mins minuty
     */
    public void setTime(int hrs, int mins){
        this.time = new Time(hrs,mins);
    }
    /**
     * Metoda wczytująca dane z plików .kin
     */
    @SuppressWarnings("unchecked")
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
            we = new ObjectInputStream(new FileInputStream("Res.kin"));
            this.ress = (ArrayList<Res>)we.readObject();
            we.close();
         }catch(IOException e){
            System.err.println("IO Error: "+e);
        }catch(ClassNotFoundException e){
            System.err.println("Class not found: "+e);
        }
    }
    /**
     * Metoda zapisująca dane do plików .kin
     */
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
            wy = new ObjectOutputStream(new FileOutputStream("Res.kin"));
            wy.writeObject(this.ress);
            wy.close();
        }catch(IOException e){
            System.err.println("IO Error: "+e);
        }
    }
    /**
     * Metoda dodająca film do listy filmów(z opisem)
     * @param name tytuł filmu
     * @param genre rodzaj
     * @param rating klasyfikacja
     * @param desc krótki opis filmu
     */
    public void addMovie(String name,String genre,String rating,String desc){        
        this.movies.add(new Movie(name,genre,rating,desc));
    }
    /**
     * Metoda dodająca film do listy filmów(bez opisu)
     * @param name tytuł filmu
     * @param genre rodzaj
     * @param rating klasyfikacja
     */
    public void addMovie(String name,String genre,String rating){        
        this.movies.add(new Movie(name,genre,rating));
    }
    /**
     * Metoda dodająca salę kinową do listy
     * @param id identyfikator sali
     */
    public void addCRoom(int id){
        this.crooms.add(new CRoom(id));
    }
    /**
     * Metoda dodająca seans do listy
     * @param movie obiekt klasy Movie
     * @param time obiekt klasy Time (dokładny czas rozpoczęcia)
     * @param croom obiekt klasy CRoom (sala kinowa)
     */
    public void addShow(Movie movie, Time time, CRoom croom ){
        int prevID;
        if(this.shows.size() == 0) prevID = 0;
        else prevID = this.shows.get(this.shows.size()-1).getID();
        this.shows.add(new Show(movie,croom,time));
        this.shows.get(this.shows.size()-1).setID(prevID+1);
    }    
    /**
     * Metoda dodająca użytkownika
     * @param name nazwa użytkownika
     * @param password hasło
     * @param utype typ(0 - zwykły klient, 1 - użytkownik kasy)
     */
    public void addUser(String name, String password,int utype){
        this.users.add(new User(name,password,utype));
    }
    /**
     * Metoda usuwająca film o danym identyfikatorze
     * @param n identyfikator filmu
     * @return -1 gdy nie ma filmu o danym ID, 0 - gdy usunięto pomyślnie
     */
    public int delMovie(int n){
        if(n>=this.movies.size()) return -1;
        this.movies.remove(n);
        return 0;
    }
    /**
     * Metoda usuwająca salę kinową o danym identyfikatorze
     * @param n identyfikator sali
     * @return -1 gdy nie ma sali o danym ID, 0 - gdy usunięto pomyślnie
     */
    public int delCRoom(int n){
        if(n>=this.crooms.size()) return -1;
        this.crooms.remove(n);
        return 0;
    }
    /**
     * Metoda usuwająca seans o danym identyfikatorze
     * @param n identyfikator seansu
     * @return -1 gdy nie ma seansu o danym ID, 0 - gdy usunięto pomyślnie
     */
    public int delShow(int n){
        if(n>=this.shows.size()) return -1;
        this.shows.remove(n);
        return 0;
    }
    /**
     * Metoda usuwająca użytkownia o danym identyfikatorze
     * @param n identyfikator użytkownika
     * @return -1 gdy nie ma użytkownika o danym ID, 0 - gdy usunięto pomyślnie
     */
    public int delUser(int n){
        if(n>=this.users.size()) return -1;
        this.users.remove(n);
        return 0;
    }
    /**
     * Metoda zwracająca listę filmów w postaci tablicy
     * @return tablica filmów Movie[]
     */
    public Movie[] getMovies(){
        //Integer[] ints = list.toArray(new Integer[]{});
        Movie[] movs = this.movies.toArray(new Movie[]{});
        return movs;
    }
    /**
     * Metoda zwracająca listę sal kinowych w postaci tablicy
     * @return tablica sal kinowych CRoom[]
     */
    public CRoom[] getCRooms(){
        CRoom[] crs = this.crooms.toArray(new CRoom[]{});
        return crs;
    }
    /**
     * Metoda zwracająca listę seansów w postaci tablicy
     * @return tablica seansów Show[]
     */
    public Show[] getShows(){
        Show[] shs = this.shows.toArray(new Show[]{});
        return shs;
    }
    /**
     * Metoda zwracająca listę użytkowników w postaci tablicy
     * @return tablica użytkowników User[]
     */
    public User[] getUsers(){
        User[] usrs = this.users.toArray(new User[]{});
        return usrs;
    }
    /**
     * Metoda wypisująca na standardowe wyjście listę filmów
     */
    public void listMovies(){
        for(int i=0;i<this.movies.size();i++){
            System.out.println(i+". "+this.movies.get(i).getName()+" Gen:"+this.movies.get(i).getGenre()+" Rat:"+this.movies.get(i).getRating());
        }
    }
    /**
     * Metoda wypisująca na standardowe wyjście listę sal kinowych
     */
    public void listCRooms(){
        for(int i=0;i<this.crooms.size();i++){
            System.out.println(i+". "+this.crooms.get(i).getID());
        }
    }
    /**
     * Metoda wypisująca na standardowe wyjście listę seansów
     */
    public void listShows(){
        for(int i=0;i<this.shows.size();i++){
            System.out.println(i+". "+this.shows.get(i).getMovie().getName()+" Room: "+this.shows.get(i).getRoom().getID()+" Time: "+this.shows.get(i).getCTime().getHour()+':'+this.shows.get(i).getCTime().getMinute());
        }
    }
    /**
     * Metoda wypisująca na standardowe wyjście listę użytkowników
     */
    public void listUsers(){
        for(int i=0;i<this.users.size();i++){
            System.out.println(i+". "+this.users.get(i).getName()+"  Type: "+this.users.get(i).getUType());
        }
    }
    public void listRess(){
        for(int i=0;i<this.ress.size();i++){
            System.out.println(i+". "+this.ress.get(i).getName()+" ShowID"+this.ress.get(i).getShowID()+" Seat:"
                    +this.ress.get(i).getSeat()[0]+"-"+this.ress.get(i).getSeat()[1]+" isok:"+this.ress.get(i).isok());
        }
    }
    /**
     * Metoda zwracająca obiekt klasy Time z czasem danego filmu
     * @return obiekt klasy Time
     */
    public Time getTime(){
        return time;
    }
}
