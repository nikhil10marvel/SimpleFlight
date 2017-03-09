/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karthik
 */
public class MainServer extends Thread {

    private int port = 0;
    protected ServerSocket server;
    protected Socket client;
    protected static SubServer[] subServers;
    protected InetAddress ipaddr;
    protected static Logger logger = Logger.getLogger(MainServer.class.getName());
    
    public MainServer(int port, int maxClients){
        try {
            this.ipaddr = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.port = port;
            server = new ServerSocket(port);
            server.setSoTimeout(10000);
            subServers = new SubServer[maxClients];
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        while(!Thread.interrupted()){
           try{
            logger.log(Level.INFO, "Listening for Clients on {0}", ipaddr.getHostAddress());
            client = server.accept();
            logger.info("ACCEPTED CONNECTION FROM " + client.getRemoteSocketAddress());
            assign();
            reset();
           } catch(Exception e){
               if(e instanceof NullPointerException){
                   logger.severe("ERROR: " + e.getCause() + " trace: " + e.getStackTrace().toString());
               } else {
                   System.out.print("net.MainServer.run() error: ");
                   e.printStackTrace();
               }
           }
        }
    }
    
    public static void broadcast(String broadcast){
        for(SubServer subServer : subServers){
            if(subServer != null) subServer.sendData(broadcast);
        }
    }
    
    public void assign(){
        for (int i = 0; i < subServers.length; i++) {
            if(subServers[i] == null){
                subServers[i] = new SubServer(i, client);
                subServers[i].start();
            }
        }
    }
    
    public void reset(){
        for (int i = 0; i < subServers.length; i++) {
            if(subServers[i] != null){
                if(subServers[i].isOccupied == false){
                    subServers[i] = null;
                }
            }
        }
    }
    
}
