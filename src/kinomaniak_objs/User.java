/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_objs;
import java.io.Serializable;

/**
 *
 * @author qbass
 */
public class User implements Serializable{
    private String name;
    private String password;
    private int utype;
    private int[] availcmds;
    
    public User(String name, String password,int utype){
        this.name = name;
        this.password = password;
        this.utype = utype;
        this.availcmds = new int[10];
    }
    
    public int[] getACmds(){
        return this.availcmds;
    }    
    public String getName(){
        return this.name;
    }
    public String getPass(){
        return this.password;
    }
    public int getUType(){
        return this.utype;
    }
    
    private void setCmds(){
        switch(this.utype){
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
                this.availcmds[0] = 0;
                this.availcmds[1] = 1;
                this.availcmds[2] = 2;
                this.availcmds[3] = 3;
                this.availcmds[4] = 4;
                this.availcmds[5] = 5;
                this.availcmds[6] = 6;
                this.availcmds[7] = 7;
                this.availcmds[8] = 666;
                this.availcmds[9] = 667;
                break;
            }
        }
    }
}
