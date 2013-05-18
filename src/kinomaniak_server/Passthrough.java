/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

/**
 *
 * @author qbass
 */
public class Passthrough {
    private static final int PORT = 8888;
    private static final int MAXCONNS = 5;
    private boolean logged;
    private String user;
    private String password;
    private int type;
//    private Server[] activeServers;
    private ServerSocket sockfd;
    private Socket[] activeConns;
    private Thread[] activeThreads;
    private int connectionCount; 
  //  private Socket acceptsockfd;
    
    public Passthrough(){
        try{
            sockfd = new ServerSocket(PORT);
        }catch(IOException e){
            System.err.println("Could not listen on port: "+PORT);  
            System.exit(1); 
        }
        this.connectionCount = 0;
//        this.activeServers = new Server[MAXCONNS];
        this.activeConns = new Socket[MAXCONNS+1];// +1 for sending error message to 'not connected' client
        this.activeThreads = new Thread[MAXCONNS];
    }
    public void waitForConnection(){ //wait for client connection
        while(1==1){ 
            if(this.connectionCount<MAXCONNS){
                //wait for client to connect
                try{
                    this.activeConns[connectionCount] = sockfd.accept();
                    this.activeThreads[connectionCount] = new Thread(new Server(this.activeConns[connectionCount]));
                    this.connectionCount++;
                    System.out.println("Connected");
                }catch(IOException e ){
                    System.err.println("Could not connect Client.");
                }
                
            }else{
                try{
                    this.activeConns[MAXCONNS+1] = sockfd.accept();
                }catch(IOException e){
                    System.err.println("Could not connect Client.");
                }
            }
        }
    }
    public boolean isLogged(){
        return this.logged;
    }
    public void setLogin(String usr,String pwd){
        this.user = usr;
        this.password = pwd;
    }
    public int checkLogin(){
        /* int uid=-1;
         * for(int i=0;users.getusers().length;i++){
         *  if(this.user == users.getusers()[i]) {
         *      uid = i;
         *      break;
         *  }
         * }if(uid == -1) return -1; //no user
         * if(sha1(this.password) == users.getpass(uid)) return users.gettypeid(uid);
         * return -2; // bad password
         */
        return -1;
    }
    /*
    private void login(){
        this.activeServers[this.connectionCount] = new Server(this.type, this.user);
        connectionCount++;
    }*/
}
