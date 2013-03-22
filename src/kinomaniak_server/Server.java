/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;
import kinomaniak_client.*;

/**
 *
 * @author qbass
 */
public class Server {
    private String loginname;
    private int logintype;
    private int[] availcmds;
    private Client[] activeconns;
    private int command;
    
    /**
     * @param args the command line arguments
     */
    public Server(int type){
        setcmds(type);
    }
    
    private void setcmds(int type){
        
    }
    
    public void sendtoclient(Client client){
        
    }
    
    public void recvcmd(){
        
    }
    
    public void processcmd(int cmd){
        if(cmd == -1){ //logoff
            //LOGOFF
        }else if(cmd == -2){ //exit
            //EXIT
        }
    }
    public void sendresp(){
        
    }
    
}
