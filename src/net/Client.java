package net;

import entities.Entity;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleflight.MainGame;

/**
 *
 * @author Nikhil
 */
public class Client extends Thread{
    
    private Socket server;
    private Logger logger = Logger.getLogger("client");
    private String host;
    private int port;
    private DataInputStream din;
    private DataOutputStream dos;
    private final MainGame game;
    private int id;
    
    

    public Client(String host, int port, MainGame game) {
        this.host = host;
        this.port = port;
        this.game = game;
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
        try {
            //Connect to Server
            logger.log(Level.INFO, "Conneting to {0} at {1}", new Object[]{host, port});
            server = new Socket(host, port);
            
            //Set input and output streams
            this.din = new DataInputStream(server.getInputStream());
            this.dos = new DataOutputStream(server.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sendData("id"); //
        process(readData()); //Disable player puppet
        
        while(!Thread.interrupted()){
            Entity player = game.entities.get(game.player_ent_num);
            sendData("mypos " + player.getX() + " " + player.getY() + " " + player.getImage().getRotation());
            logger.info("Sent positional signal");
            process(readData());
            
        }
        
        
    }
    
    protected void process(String s){
        String[] toki = s.split(" ");
        if(s.startsWith("urid")){
            game.players[id].disable(); //Disabling the player's additional rendering
        }
        if(s.startsWith("plrpos")) {
            float px = Float.parseFloat(toki[2]);
            float py = Float.parseFloat(toki[3]);
            float prot = Float.parseFloat(toki[4]);
            int id = Integer.parseInt(toki[1]);
            
            game.players[id].setX(px);
            game.players[id].setY(py);
            game.players[id].setAngle(prot);
        }
    }
}
