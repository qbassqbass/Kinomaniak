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
    private final int cmdCount = 10;
    private Client[] activeconns;
    private int command;
    
    /**
     * @param args the command line arguments
     */
    public Server(int type){
        this.availcmds = new int[10];
        this.setCmds(type);        
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
