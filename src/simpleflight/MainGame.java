package simpleflight;

import entities.Puppet;
import entities.Entity;
import entities.Plane;
import entities.Puppet;
import java.util.ArrayList;
import java.util.logging.Logger;
import net.Client;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Nikhil
 */
public class MainGame extends BasicGameState{
    
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public Puppet[] players = new Puppet[SimpleFlight.maxCli-1];
    Image background;
    public int player_ent_num;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/background.png");
        Entity e = new Plane();
        entities.add(e);
        player_ent_num = entities.indexOf(e);
        entities.forEach((entity) -> {
            entity.init(gc, sbg);
        }); 
        if(SimpleFlight.Client){
            Client cli = new Client("192.168.1.37", 6444, this);
            cli.start();
            Logger.getLogger(Client.class.getName()).info("Started Client");
        }
        
        for (int i = 0; i < players.length; i++) {
            players[i] = new Puppet();
            players[i].setImage("res/entities/Plane.png");
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();  //Background
        entities.forEach((entity) -> {
            entity.render(gc, sbg, grphcs);
        });
        for (int i = 0; i < players.length; i++) {
            Puppet player = players[i];
            player.render();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        entities.forEach((entity) -> {
            entity.tick(gc, sbg, delta);
        });
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
