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
    private int command;
    private User luser;
    
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
    }
    
    @Override
    public void run(){ //todo!
        try{
            final PrintWriter out = new PrintWriter(this.sockfd.getOutputStream(),true);  
            final BufferedReader in = new BufferedReader(new InputStreamReader(this.sockfd.getInputStream()));
            final ObjectInputStream oin = new ObjectInputStream(this.sockfd.getInputStream());
            this.luser = (User)oin.readObject(); // odczyt obiektu użytkownika od klienta
        }catch(IOException e){
            System.err.println("Error!");
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
    
    public void sendToClient(Client client){
        
    }
    
    public void recvCmd(){
        
    }
    
    public void processCmd(int cmd){
        if(cmd == -1){ //logoff
            //LOGOFF
        }else if(cmd == -2){ //exit
            //EXIT
        }
    }
    public void sendResp(){
        
    }
    
}
