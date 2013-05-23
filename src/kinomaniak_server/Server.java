/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;
import kinomaniak_client.*;
import kinomaniak_objs.*;
import java.net.Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 *
 * @author qbass
 */
public class Server  implements Runnable{
//    private String user;
//    private String password;
    private Socket sockfd;
//    private int logintype;
//    private int[] availcmds;
//    private final int cmdCount = 10;
//    private Client[] activeconns;
    private boolean logged;
//    private int command;
    private User luser;
     private PrintWriter out;  //out for data
     private ObjectOutputStream oout; // output for objects
     private BufferedReader in; //in for data
     private ObjectInputStream oin; //input for objects
     
    
    /**
     * @param args the command line arguments
     */
    /*
    public Server(int type,String username){
        this.loginname = username;
        this.availcmds = new int[10];
        this.setCmds(type);        
    }
    */
    public Server(Socket sockfd){
        this.sockfd = sockfd;
        try{
            this.out = new PrintWriter(this.sockfd.getOutputStream(),true);  //out for data
            this.oout = new ObjectOutputStream(this.sockfd.getOutputStream()); // output for objects
            this.in = new BufferedReader(new InputStreamReader(this.sockfd.getInputStream())); //in for data
            this.oin = new ObjectInputStream(this.sockfd.getInputStream()); //input for objects
            this.logged = true;
        }catch(IOException e){
            System.err.println("IOError!");
        }
    }
    
    @Override
    public void run(){ //todo!
        boolean cmdAvail = false;
        String tmp;
        try{
            this.out.write("!OK!");
            this.luser = (User)oin.readObject(); // odczyt obiektu użytkownika od klienta        
            boolean uok = checkUser(); // sprawdzenie użytkownika
            if(uok) this.out.write("!UOK");
            else{
                this.logged = false;
                this.out.write("!ERROR!");
                this.in.close();
                this.out.close();
                this.oin.close();
                this.oout.close();
                System.exit(-1);
            }
            tmp = this.in.readLine();
            ObjectInputStream we = new ObjectInputStream(new FileInputStream("movies.db"));
            if(tmp.equals("!GETMOV!")){
                this.out.write("!OK!");
                this.oout.writeObject(we);
                //this.oout.writeObject(MoviesDB);
            }else if(tmp.equals("!GETMOVDT!")){
                tmp = this.in.readLine();
                String dbDate = (String)we.readObject();
                if(tmp.equals(dbDate)) this.out.write("!MOVOK!");
                else { this.out.write("!MOVUPD!"); this.oout.writeObject(we); }
                //if(tmp.equals(newestDBdate)) this.out.write("!MOVOK!");
                //else{ this.out.write("!MOVUPD!"); this.oout.writeObject(MoviesDB); }
            }
            while(!tmp.equals("!RDY!")){
                tmp = this.in.readLine();                
                this.out.write("Waiting for RDY command...");
            }
            this.out.write("!RDY!");            
            while (this.logged){
                if(in.ready()){ //sprawdzenie dostępności danych w buforze wejścia 
                    String data = in.readLine();
                    if(data.equals("!CMD!")){ //jeśli klient wysyła komendę
                        cmdAvail = true;
                    }else if(data.equals("!OK!")){ //jeśli klient przesyła potwierdzenie
                        
                    }else{
                        out.write("!NAVAIL!"); //not available
                    }
                    if(cmdAvail)
                        if(in.ready()){
                            int cmd = in.read();
                            if(this.checkGrants(cmd)){
                                this.processCmd(cmd);
                                out.write("!OK!");
                            }
                            else out.write("!NGRANT!"); //not granted
                            
                        }
                    cmdAvail = false;
                }
            }
            in.close();
            oin.close();
            out.close();
            oout.close();
        }catch(IOException e){
            System.err.println("IOError!");
        }catch(ClassNotFoundException e){
            System.err.println("Class not found");
        }
    }
    
    public boolean checkGrants(int cmd){
        if(cmd < 0) return true;
        for(int i=0;i<12;i++){
            if(cmd == luser.getACmds()[i]){
                return true;
            }
        }
        return false;
    }
    
    public void processCmd(int cmd){
        switch(cmd){
            case -1 : {
                this.logged = false;
                break;
            }
            case 1 :{
                
                break;
            }
            case 2:{
                
                break;
            }
            case 3 :{
                
                break;
            }
            case 4:{
                
                break;
            }
            case 5:{
                
                break;
            }
            case 6:{
                
                break;
            }
            case 7 :{
                
                break;
            }
            case 8:{
                
                break;
            }
            case 666 :{
                
                break;
            }
            case 667:{
                
                break;
            }
            default:{
                out.write("!NOCMD!");
            }
            
     }
    }
    private boolean checkUser(){
        String usr = this.luser.getName();
        String pwd = this.luser.getPass();//sha1pass
        boolean log = false;
        try{
            BufferedReader userfile = new BufferedReader(new InputStreamReader(new FileInputStream("users.txt")));
            String temp;
            while((temp = userfile.readLine()) != null){
                String[] tmp = temp.split(":");
                if(tmp[0].equals(usr)) if(tmp[1].equals(pwd)) log = true;
            }
        }catch(IOException e){
            System.err.println("IO Error");
        }
        return log;
    }
    public void sendResp(){
        
    }
    private Object getObj(String file){
        try{
            ObjectInputStream we = new ObjectInputStream(new FileInputStream(file));
            Object obj = we.readObject();
            return obj;
        }catch(IOException e){
            System.err.println("BÅ‚Ä…d odczytu pliku");
        }catch(ClassNotFoundException c){
            System.err.println("Brak klasy Object xD");
        } 
        return null;
    } 
    
    
     private String toSHA1(byte[] pass) {
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-1");
        }catch(NoSuchAlgorithmException e){
            System.err.println("No Such Algorithm Exception: "+e);
            return null;
        }
        return byteToHex(md.digest(pass));
    }
    
    private String byteToHex(byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
}
