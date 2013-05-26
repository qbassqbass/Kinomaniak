/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_mgmt;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import kinomaniak_objs.*;
import java.util.Scanner;
/**
 *
 * @author qbass
 */
public class MgmtApp {
    private static boolean loaded = false;
    
    private static int menu(){
        System.out.println("==MENU==");
        System.out.println("-1. Generate placeholder data");
        System.out.println("0. Load data");
        if(loaded){
            System.out.println("1. Save data");
            System.out.println("2. Add Movie");
         //   System.out.println("3. (N/A)Add Time");
            System.out.println("4. Add CRoom");
            System.out.println("5. Add Show");
            System.out.println("6. List Movies");
            System.out.println("7. List CRooms");
            System.out.println("8. List Shows");
            System.out.println("9. Delete Movie");
            System.out.println("10. Delete CRoom");
            System.out.println("11. Delete Show");
        }
        System.out.println("666. Kill me");
        Scanner in = new Scanner(System.in);
        System.out.print("Your choice: ");
        try{
            int c = in.nextInt();
            return c;
        }catch(InputMismatchException e){
            System.err.println("You have to input a number!");
        }
       return -666;
    }
    
    public static void main(String[] args){
        System.out.println("==Welcome in Movie DataBase Management System==");
        MovieDBMgmt mgmt = new MovieDBMgmt();
        while(true){
        int tmp = menu();
        switch(tmp){
            case -1:{
                Movie[] mov = new Movie[1];
                mov[0] = new Movie("Heroes","Action","Best");
                CRoom[] cr = new CRoom[1];
                cr[0] = new CRoom(1);
                Time[] tm = new Time[1];
                tm[0] = new Time(12,10);
                Show[] sh = new Show[1];
                sh[0] = new Show(mov[0],cr[0],tm[0]);
                try{
                    ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Movies.kin"));
                    wy.writeObject(mov);
                    wy.close();
                    wy = new ObjectOutputStream(new FileOutputStream("CRooms.kin"));
                    wy.writeObject(cr);
                    wy.close();
                    wy = new ObjectOutputStream(new FileOutputStream("Shows.kin"));
                    Date dateNow = new Date (); 
                    SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
                    StringBuilder sdt = new StringBuilder( dt.format( dateNow ) );
                    String st = sdt.toString();
                    wy.writeObject(st);
                    wy.writeObject(sh.length);
                    wy.writeObject(sh);
                    wy.close();
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                }
                break;
            }
            case 0:{
                mgmt.getData();
                System.out.println("Data loaded...");
                loaded = true;
                break;
            }
            case 1:{
                mgmt.saveData();
                System.out.println("Data saved...");
                break;
            }
            case 2:{
                Scanner in = new Scanner(System.in);
                System.out.print("Movie Title: ");
                String name = in.nextLine();
                System.out.print("Movie Genre: ");
                String genre = in.nextLine();
                System.out.print("Movie Rating: ");
                String rating = in.nextLine();
                mgmt.addMovie(name,genre,rating);
                System.out.println("Movie added");
                break;
            }
            case 3:{
                Scanner in = new Scanner(System.in);
                System.out.print("Hour: ");
                int h = in.nextInt();
                System.out.print("Minute: ");
                int m = in.nextInt();
                mgmt.setTime(h, m);
                System.out.println("Time added");
                break;
            }
            case 4:{
                Scanner in = new Scanner(System.in);
                System.out.print("ID: ");
                int id = in.nextInt();
                mgmt.addCRoom(id);
                System.out.println("CRoom added");
                break;
            }
            case 5:{
                Scanner in = new Scanner(System.in);
                System.out.println("List of Movies: ");
                mgmt.listMovies();
                System.out.print("Your Choice: ");
                int mov = in.nextInt();
                System.out.println("List of CRooms: ");
                mgmt.listCRooms();
                System.out.print("Your Choice: ");
                int cr = in.nextInt();
                System.out.println("Time: ");
                System.out.print("Hour: ");
                int h = in.nextInt();
                System.out.print("Minutes: ");
                int m = in.nextInt();
                mgmt.setTime(h,m);
                mgmt.addShow(mgmt.getMovies()[mov],mgmt.getTime(),mgmt.getCRooms()[cr]);
                System.out.println("Show added");
                break;
            }
            case 6:{
                mgmt.listMovies();
                System.out.println("\nPress Enter to continue...");
                Scanner sc = new Scanner(System.in);
                while(!sc.nextLine().equals(""));
                break;
            }
            case 7:{
                mgmt.listCRooms();
                System.out.println("\nPress Enter to continue...");
                Scanner sc = new Scanner(System.in);
                while(!sc.nextLine().equals(""));
                break;
            }
            case 8:{
                System.out.flush();
                mgmt.listShows();
                System.out.println("\nPress Enter to continue...");
                Scanner sc = new Scanner(System.in);
                while(!sc.nextLine().equals(""));
                break;
            }
            case 9:{
                //delete Movie
                break;
            }
            case 10:{
                //delete CRoom
                break;
            }
            case 11:{
                //delete Show
                break;
            }
            case 666:{
                System.out.println("Goodbye...");
                System.exit(1);
            }
            default:{
                System.out.println("You have to choose proper option!");
                break;
            }
        }
    }
    }
}
