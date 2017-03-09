package entities;

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Nikkhil
 */
public abstract class Entity {
    protected float x, y;
    protected float width, height;
    protected float velX, velY;
    protected Image image;
    protected Rectangle hitbox = new Rectangle();
    
    public abstract void init(GameContainer gc, StateBasedGame sbg);
    public abstract void tick(GameContainer gc, StateBasedGame sbg, int delta);
    public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics graphics);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String image) {
        try {
            this.image = new Image(image);
        } catch (SlickException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
    
    public boolean intersects(Entity other){
        hitbox.setBounds((int)x, (int)y, (int)width, (int)height);
        boolean res = hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
        return res;
    }
}
