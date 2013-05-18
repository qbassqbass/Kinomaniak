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
/**
 *
 * @author qbass
 */
public class Server  implements Runnable{
    private String user;
    private String password;
    private Socket sockfd;
    private int logintype;
    private int[] availcmds;
    private final int cmdCount = 10;
//    private Client[] activeconns;
    private boolean logged;
    private int command;
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
        try{
            this.luser = (User)oin.readObject(); // odczyt obiektu użytkownika od klienta           
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
                            if(this.checkGrants(cmd)) this.processCmd(cmd);
                            else out.write("!NGRANT!"); //not granted
                            
                        }
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
    
    private void setCmds(int type){
        switch(type){
            case 0 : { //Client
                this.availcmds[0] = 4;
                break;
            }
            case 1 : { //User
                this.availcmds[0] = 4;
                this.availcmds[1] = 5;
                this.availcmds[2] = 6;
                this.availcmds[3] = 7;
                break;
            }
            case 2 : { //SuperUser/Admin
                
                break;
            }
        }
    }
    
    public boolean checkGrants(int cmd){
        for(int i=0;i<10;i++){
            if(cmd == luser.getACmds()[i]){
                return true;
            }
        }
        return false;
    }
    
    public void processCmd(int cmd){
        switch(cmd){
            case -2 :{
                
                break;
            }
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
            
     }
    }
    public void sendResp(){
        
    }
    
}
