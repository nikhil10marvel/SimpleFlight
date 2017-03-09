
package simpleflight;

import net.Client;
import net.MainServer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;

/**
 *
 * @author Nikhil
 */
public class SimpleFlight extends StateBasedGame{

    /**
     * @param args the command line arguments
     */
    
    public static int maxCli = 3;
    
    public static BasicGameState[] states = {
        new MainGame()
    };
    public static boolean Client = false;
    
    public static void main(String[] args) throws SlickException {
        if(args[0].equals("-server")){
            MainServer mainServer = new MainServer(6444, maxCli);
            mainServer.start();
        } else if(args[0].equals("-client")){
            Client = true;
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleFlight("Simple Flight 1.0"), 800, 600, false);
            appgc.start();
        } else {
            // TODO code application logic here
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleFlight("Simple Flight 1.0"), 800, 600, false);
            appgc.start();
        }
    }
    public final String name;

    public SimpleFlight(String name) {
        super(name);
        this.name = name;
        for(BasicGameState state : states){
            this.addState(state);
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        int main = 0;
        for (int i = 0; i < states.length; i++) {
            BasicGameState state = states[i];
            state.init(gc, this);
            if(state.getID() == 0){
                main = i;
            }
        }
        this.enterState(main);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
