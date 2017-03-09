package entities;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Nikhil
 */
public class Puppet {
    
    protected Image image;
    protected float x,y;
    protected float angle;
    protected boolean active = true;
    
    public void render() throws SlickException{
        if(active){
            image.draw(x, y);
            image.setRotation(angle);
        }
    }
    
    public void disable(){
        active = false;
    }
    
    public void enable(){
        active = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) throws SlickException {
        this.image = new Image(path);
    }

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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
    
    
    
}
