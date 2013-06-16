/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author qbass
 */
public class Passthrough {
    
    /**
     * Główna metoda do obsługi połączeń ze strony klienta do wątków serwera.
     * @param args
     */
    public static void main(String[] args){
        Log logger = new Log();
        final int PORT = 8888;
        ServerSocket sockfd;
        System.out.println("Waiting for connection");
            try{
                sockfd = new ServerSocket(PORT);
                while(1==1){ 
//                    wait for client to connect
                    try{
                        Socket tmpsockfd = sockfd.accept();
                        System.out.println("Client connected from "+tmpsockfd.getInetAddress().getHostAddress());
                        String st = new StringBuilder(new SimpleDateFormat("dd-mm-yyyy").format(new Date())).toString();
                        logger.doLog(0,"separator");
                        logger.doLog(0,"Client connected from "+tmpsockfd.getInetAddress().getHostAddress());
                        new Thread(new Server(tmpsockfd)).start();
                    }catch(IOException e ){
                        System.err.println("Could not connect Client.");
                    }
            }
            }catch(IOException e){
                System.err.println("Could not listen on port: "+PORT);  
                System.exit(1); 
            }
            
    }
}
