/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Karthik
 */
public class Plane extends Entity{
    
    protected Input input;
    float speed = 0.5f;
    float mouseX, mouseY;
    double angle = 0;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        setImage("res/entities/Plane.png");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick(GameContainer gc, StateBasedGame sbg, int delta) {
        x += velX;
        y += velY;
        
        if(x >= 740){
            x -= 1;
        }
        if(x <= 1){
            x += 1;
        }
        
        try{
            mouseX = input.getMouseX();
            mouseY = input.getMouseY(); 
            float xdis = mouseX - (x + (image.getWidth()/2));
            float ydis = mouseY - (y + (image.getHeight()/2));
            angle = Math.toDegrees(Math.atan2(ydis, xdis)) ;
            
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
        
        input = gc.getInput();
        
        //Forward
        if(input.isKeyDown(input.KEY_W)) velY = speed*-1;
        
        //Backward
        else if(input.isKeyDown(input.KEY_S)) velY = speed;
        
        //Sideways
        else if(input.isKeyDown(input.KEY_A)) velX = speed*-1;
        else if(input.isKeyDown(input.KEY_D)) velX = speed;
        
        //Mouse Input
        //Firing etc... In Dev
        
        else {velX = 0; velY = 0;}
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics) {
        image.draw(x, y);
        graphics.drawString(x+" "+y, 100, 10);
        image.setRotation((float)angle);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
