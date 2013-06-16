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
import java.io.EOFException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
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
     private Log logger;
     private String threadName;
    
    
    /*
    public Server(int type,String username){
        this.loginname = username;
        this.availcmds = new int[10];
        this.setCmds(type);        
    }
    */
     /**
      * Konstruktor wątku serwera
     * @param sockfd deskryptor gniazda dla podłączonego klienta
     */
    public Server(Socket sockfd){
        this.sockfd = sockfd;
        try{
            this.out = new PrintWriter(this.sockfd.getOutputStream(),true);  //out for data
            this.oout = new ObjectOutputStream(this.sockfd.getOutputStream()); // output for objects
            this.in = new BufferedReader(new InputStreamReader(this.sockfd.getInputStream())); //in for data
            this.oin = new ObjectInputStream(this.sockfd.getInputStream()); //input for objects
            this.logged = true;
            this.logger = new Log();
            this.threadName = Thread.currentThread().getName();
        }catch(IOException e){
            System.err.println("IOError from "+sockfd.getInetAddress().getHostAddress()+": "+e);
        }
    }
    
    private void endThread(){
        try{
            in.close();
            oin.close();
            out.close();
            oout.close();
            sockfd.close();
            return;
        }catch(IOException e){
            
        }
    }
    
    @Override
    public void run(){ //todo!
        System.out.println(this.threadName+": "+sockfd.getInetAddress().getHostAddress());
        logger.doLog(this.threadName+": "+sockfd.getInetAddress().getHostAddress());
        boolean cmdAvail = false;
        String tmp;
        try{
//            this.out.write("!OK!");
            this.oout.writeObject((String)"!OK!");
            this.luser = (User)oin.readObject(); // odczyt obiektu użytkownika od klienta        
            boolean uok = checkUser(); // sprawdzenie użytkownika
            if(uok) this.oout.writeObject((String)"!UOK!");
            else{
                this.logged = false;
                this.oout.writeObject((String)"!ERROR!");
                this.in.close();
                this.out.close();
                this.oin.close();
                this.oout.close();
                System.exit(-1);
            }
            tmp = (String)oin.readObject();
            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Shows.kin"));
            switch (tmp) {
                case "!GETMOV!":
                    this.oout.writeObject((String)"!OK!");
                    String date = (String)we.readObject();
                    System.out.println("Debug pre");
                    List<Show> ssstmp = (ArrayList<Show>)we.readObject(); 
                    this.oout.writeObject(ssstmp);
                    break;
                case "!GETMOVDT!":
                    tmp = (String)oin.readObject();
                    String dbDate = (String)we.readObject();
                    if(tmp.equals(dbDate)) this.oout.writeObject((String)"!MOVOK!");
                    else { this.oout.writeObject((String)"!MOVUPD!"); this.oout.writeObject(we); }
                    break;
            }
            while(!tmp.equals("!RDY!")){
                tmp = (String)oin.readObject();
            }
            this.oout.writeObject((String)"!RDY!");            
            while (this.logged){
                    String data = (String)oin.readObject();
                    switch (data) {
                        case "!CMD!":
//                            jeśli klient wysyła komendę
                            this.oout.writeObject((String)"!OK!");
                            cmdAvail = true;
                            break;
                        case "!OK!":
                            break;
                        default:
                            this.oout.writeObject((String)"!NAVAIL!"); //not available
                            break;
                    }
                    if(cmdAvail){
                            int cmd = (Integer)oin.readObject();
                            if(this.checkGrants(cmd)){
                                this.processCmd(cmd);
                            }
                            else this.oout.writeObject((String)"!NGRANT!"); //not granted
                    }
                        
                    cmdAvail = false;
            }
            this.endThread();
        }catch(EOFException e){
             System.err.println("Connection closed: "+sockfd.getInetAddress().getHostAddress());
             logger.doLog(this.threadName+": Connection closed: "+sockfd.getInetAddress().getHostAddress());
             this.endThread();
        }catch(IOException e){
            System.err.println("IOError from "+sockfd.getInetAddress().getHostAddress()+": "+e);
            logger.doLog(this.threadName+": IOError from "+sockfd.getInetAddress().getHostAddress()+": "+e);
            this.endThread();
        }catch(ClassNotFoundException e){
            System.err.println("Class not found from "+sockfd.getInetAddress().getHostAddress()+": "+e);
            logger.doLog(this.threadName+": Class not found from "+sockfd.getInetAddress().getHostAddress()+": "+e);
        }
    }
    
    /**
     * Metoda sprawdzająca czy zalogowany użytkownik ma prawo do wykonania danej komendy
     * @param cmd identyfikator komendy 
     * @return true - jeśli dany użytkownik ma uprawnienia do wykonania, false - jeśli nie
     */
    public boolean checkGrants(int cmd){
        if(cmd < 0) return true;
        for(int i=0;i<12;i++){
            if(cmd == luser.getACmds()[i]){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda wykonująca komendę podaną przez użytkownika
     * @param cmd identyfikator komendy
     */
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
            case 5:{ // rezerwacja biletu
                try{
                    this.oout.writeObject((String)"!GDATA!");
                    String tmp = (String)oin.readObject();
                    if(tmp.equals("!OK!"))
                        this.oout.writeObject((String)"!NAZW!");
                        String nazwa = (String)oin.readObject();
                        this.oout.writeObject((String)"!SEANS!");
                        int showid = (Integer)oin.readObject();
//                        this.oout.writeObject((String)"!MIEJSC!");
                        int[][] seat = (int[][])oin.readObject();
                        Res res = new Res(nazwa,showid,seat.length,seat);
                    synchronized (this){
                        try{
                            File r = new File("Res.kin");
                            Res ares[];
                            List<Res> reslist = new ArrayList<Res>();
                            int num = 0;
                            if(!r.exists()){
                                r.createNewFile();
                                reslist.add(res);
                            }else{
                                ObjectInputStream we = new ObjectInputStream(new FileInputStream("Res.kin"));
                                reslist = (ArrayList<Res>)we.readObject();
                                we.close();
                                reslist.add(res);
                            }
                            ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Res.kin"));
                            wy.writeObject(reslist);
                            wy.close();
                        }catch(IOException e){
                            System.err.println("IO Error from "+sockfd.getInetAddress().getHostAddress()+": "+e);
                        }
                    }
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                    logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }
                break;
            }
            case 6:{// potwierdzenie rezerwacji
                try{
                    this.oout.writeObject((String)"!GDATA!");
                    String tmp = (String)oin.readObject();           
                    List<Res> reslist = new ArrayList<Res>();
                    if(tmp.equals("!OK!")){
                        File r = new File("Res.kin");
                            if(!r.exists()){
                                r.createNewFile();
                            }
                         synchronized(this){
                            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Res.kin"));
                            reslist = (ArrayList<Res>)we.readObject();
                            we.close();
                         }
                         Res restmp[] = new Res[reslist.size()];
                         restmp = reslist.toArray(restmp);
                         this.oout.writeObject((Res[])restmp);
                        this.oout.writeObject((String)"!GORES!"); // Get Object Res
                        Res res = (Res)oin.readObject();
                        synchronized(this){
                            boolean acc = false;
                            for(int i =0;i<reslist.size();i++){
                                if(reslist.get(i).equals(res)){
                                    reslist.get(i).accept();
                                    acc = true;
                                    break;
                                }
                            }
                            if(acc){
                                ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Res.kin"));
                                wy.writeObject(reslist);
                                wy.close();
                                this.oout.writeObject((String)"!OK!");
                            }else this.oout.writeObject((String)"!NORES!");
                        }
                    }
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                    logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }
                break;
            }
            case 7 :{ // odbiór rezerwacji
                try{
                    this.oout.writeObject((String)"!GDATA!");
                    String tmp = (String)oin.readObject();
                    List<Res> reslist = new ArrayList<Res>();
                    if(tmp.equals("!OK!")){
                        File r = new File("Res.kin");
                            if(!r.exists()){
                                r.createNewFile();
                            }
                        synchronized(this){
                            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Res.kin"));
                            reslist = (ArrayList<Res>)we.readObject();
                            we.close();
                         }
                        Res restmp[] = new Res[reslist.size()];
                        restmp = reslist.toArray(restmp);
                        this.oout.writeObject((Res[])restmp);
                        this.oout.writeObject((String)"!GORES!"); // Get Object Res
                        Res res = (Res)oin.readObject();
                        synchronized(this){                            
                            boolean ok = false;
                            System.out.println("Checking");
                            for(int i = 0;i<reslist.size();i++){
                                if(reslist.get(i).equals(res)){
                                    reslist.get(i).get();
                                    System.out.println(i);
                                    ok = true;
                                    break;
                                }
                            }
                            if(ok){
                                ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Res.kin"));
                                wy.writeObject(reslist);
                                wy.close();                                
                                this.oout.writeObject((String)"!OK!");
                            }else this.oout.writeObject((String)"!NORES!");
                        }
                    }
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                    logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }
                break;
            }
            case 8:{ // anulowanie rezerwacji
                try{
                    this.oout.writeObject((String)"!GDATA!");
                    String tmps = (String)oin.readObject();
                    List<Res> reslist = new ArrayList<Res>();
                    if(tmps.equals("!OK!")){
                        File r = new File("Res.kin");
                            if(!r.exists()){
                                r.createNewFile();
                            }
                         synchronized(this){
                            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Res.kin"));
                            reslist = (ArrayList<Res>)we.readObject();
                            we.close();
                         }
                        Res restmp[] = new Res[reslist.size()];
                        restmp = reslist.toArray(restmp);
                        this.oout.writeObject((Res[])restmp);
                        this.oout.writeObject((String)"!GORES!"); // Get Object Res
                        Res res = (Res)oin.readObject();
                        synchronized(this){
                            int tmp = -254;
                            for(int i = 0;i<reslist.size();i++){
                               if(reslist.get(i).equals(res)){
                                    tmp = i;
                                    reslist.remove(i);
                                    break;
                                }
                            }
                            if(tmp>-1){
                                ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream("Res.kin"));
                                wy.writeObject(reslist);
                                wy.close();                                
                                 this.oout.writeObject((String)"!OK!");
                            }else  this.oout.writeObject((String)"!NORES!");
                        }
                    }
                }catch(EOFException e){
                     System.err.println("Connection closed: "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                      logger.doLog(this.threadName+":Connection closed from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                    logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }
                break;
            }
            case 9:{ // sprawdzenie zajętości miejsc
                try{
                    this.oout.writeObject((String)"!GDATA!");
                    String tmps = (String)oin.readObject();
                    List<Res> reslist = new ArrayList<Res>();
                    int reserved[][] = new int[10][10];
                    if(tmps.equals("!OK!")){
                        this.oout.writeObject((String)"!GSID!");
                        int showid = (Integer)this.oin.readObject();
                        File r = new File("Res.kin");
                            if(!r.exists()){
                                for(int[] i : reserved){
                                    for(int j : i){
                                        j = 0;
                                    }
                                }
                                this.oout.writeObject(reserved);
                                break;
                            }
                         synchronized(this){
                            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Res.kin"));
                            reslist = (ArrayList<Res>)we.readObject();
                            we.close();
                         }
                         for(Res rs : reslist){
                             if(rs.getShowID() == showid){
                                 int seats[][] = rs.getSeats();
                                 for(int[] i : seats){
                                     reserved[i[0]][i[1]] = 1;
                                 }
                             }
                         }
                         this.oout.writeObject(reserved);
                    }
                }catch(EOFException e){
                     System.err.println("Connection closed: "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                      logger.doLog(this.threadName+":Connection closed from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(IOException e){
                    System.err.println("IO Error: "+e);
                    logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
                }
                break;
            }
            case 10:{
                
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
            ObjectInputStream we = new ObjectInputStream(new FileInputStream("Users.kin"));
            try {
                List<User> ar = (ArrayList<User>)we.readObject();
                User[] tmp = ar.toArray(new User[]{}); 
                we.close();
                int ctmp = 0;
                for(int i=0;i<tmp.length;i++){
                    if(usr.equals(tmp[i].getName())){
                        ctmp = i;
                        break;
                    }
                }
                if(pwd.equals(tmp[ctmp].getPass())){
                    log = true;
                }
            } catch(ClassNotFoundException e){
                    System.err.println("Class not found :"+e);
                    logger.doLog(this.threadName+": Class not found from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
            }
        }catch(IOException e){
            System.err.println("IO Error from "+sockfd.getInetAddress().getHostAddress()+": "+e);
             logger.doLog(this.threadName+": IO Error from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
        }
        return log;
    }
    private Object getObj(String file){
        try{
            ObjectInputStream we = new ObjectInputStream(new FileInputStream(file));
            Object obj = we.readObject();
            return obj;
        }catch(IOException e){
            System.err.println("Błąd odczytu pliku");
            logger.doLog(this.threadName+": Błąd odczytu pliku from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
        }catch(ClassNotFoundException c){
            System.err.println("Brak klasy Object");
        } 
        return null;
    } 
    
    
     private String toSHA1(byte[] pass) {
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-1");
        }catch(NoSuchAlgorithmException e){
            System.err.println("No Such Algorithm Exception: "+e);
            logger.doLog(this.threadName+": No Such Algorithm Exception from "+this.sockfd.getInetAddress().getHostAddress()+": "+e);
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
