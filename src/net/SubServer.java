package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikhil
 */
public class SubServer extends Thread{

    protected final int id;
    protected Socket client;
    private DataOutputStream dos;
    private DataInputStream din;
    protected boolean isOccupied = false;
    
    public SubServer(int id, Socket conn){
        this.id = id;
        try {
            this.client = conn;
            this.dos = new DataOutputStream(client.getOutputStream());
            this.din = new DataInputStream(client.getInputStream());
            this.isOccupied = true;
        } catch (IOException ex) {
            Logger.getLogger(SubServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(String data){
        try {
            dos.writeUTF(data);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readData(){
        String line = null;
        try {
           line = din.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line;
    }
    
    public void run(){
        while(!Thread.interrupted()){
            String in = readData();
            Logger.getLogger("subServer").info("net.SubServer.run() " + in);
            process(in);
        }
    }
    
    public void process(String string){
        String toki[] = string.split(" ");
        if(string.startsWith("id")){
            sendData("urid " + this.id);
        }
        if(string.startsWith("mypos")){
            float x = Float.parseFloat(toki[1]);
            float y = Float.parseFloat(toki[2]);
            float rot = Float.parseFloat(toki[3]);
            
            MainServer.broadcast("plrpos " + this.id + " " + x + " " + y + " " + rot);
            
        }
    }
    
}
