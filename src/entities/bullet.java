/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Karthik
 */
public class bullet extends Entity{

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        setImage("res/entities/bullet.png");
        velX = 0;
        velY = 0;
    }

    @Override
    public void tick(GameContainer gc, StateBasedGame sbg, int delta) {
        
        x += velX;
        y += velY;
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
