/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author qbass
 */
public class Log {
    
    public Log(){
        File dir = new File("logs");
        if(!dir.exists()) dir.mkdir();
    }
    
    public void doLog(String tolog){
        PrintWriter out = null;
        String fname = "logs/"+new StringBuilder(new SimpleDateFormat("dd-mm-yyyy").format(new Date())).toString()+".log";
        try{
            File fil = new File(fname);
            if(!fil.exists()) fil.createNewFile();
            out = new PrintWriter(new BufferedWriter(new FileWriter(fname,true)));
            if(tolog.equals("separator")){
//                Date dateNow = new Date (); 
//                SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
//                StringBuilder sdt = new StringBuilder( dt.format( dateNow ) );
//                String st = sdt.toString();
                String st = new StringBuilder(new SimpleDateFormat("kk:mm:ss").format(new Date())).toString();
                out.println("===="+st+"====");
            }else{
                out.println(new StringBuilder(new SimpleDateFormat("kk:mm:ss").format(new Date())).toString()+"  "+tolog);
            }
            out.close();
        }catch(IOException e){
            System.err.println("IOError: "+e);
        }finally{
            if(out != null) out.close();
        }
    }
    
}
