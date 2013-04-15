/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;

/**
 *
 * @author qbass
 */
public class Passthrough {
    private boolean logged;
    private String user;
    private String password;
    private int type;
    
    public Passthrough(){
        
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
         * for(int i=0;users.getusers.length;i++){
         *  if(this.user == users.getusers[i]) {
         *      uid = i;
         *      break;
         *  }
         * }if(uid == -1) return -1; //no user
         * if(sha1(this.password) == users.getpass(uid)) return users.gettypeid(uid);
         * return -2; // bad password
         */
        return -1;
    }
    private void login(){
        this.logged = true;
        Server srv = new Server(this.type);
    }
}
