/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import kinomaniak_objs.*;

/**
 *
 * @author qbass
 */
public class Client {
    private PrintWriter out;
    private ObjectOutputStream oout;
    private BufferedReader in;
    private ObjectInputStream oin;
    private Socket sockfd;
    private final int PORT = 8888;
    private final String host = "localhost";
    
    public Client(){
        try{
            InetAddress addr = InetAddress.getByName(host);        
            this.sockfd = new Socket(addr,PORT);
            System.out.println("Connected");
            this.out = new PrintWriter(this.sockfd.getOutputStream(),true);  //out for data
            this.oout = new ObjectOutputStream(this.sockfd.getOutputStream()); // output for objects
            this.in = new BufferedReader(new InputStreamReader(this.sockfd.getInputStream())); //in for data
            this.oin = new ObjectInputStream(this.sockfd.getInputStream()); //input for objects
            System.out.println("Debug");
            System.out.println((String)this.oin.readObject());
        }catch(IOException e){
            System.out.println("IO Error: "+e);
        }catch(ClassNotFoundException e){
            System.out.println("Class not found: "+e);
        }
    }
    public void doit(){
        
    }
}
