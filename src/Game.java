import java.awt.*;
import java.applet.Applet;

public class Game extends Applet implements Runnable {

    private boolean active;

    private Frog player;

    private int objs;
    private int frog_saved;
    /**
     * Creates new form Game
     */
    public Game() {
      active = false;
      player = new Frog(10, 10);
      objs = 10;
      frog_saved = 0;
    }

    public boolean isActive() {
        return active;
    }

    public void run() {
        while(true) {
            // Update screen
            repaint();
            try {
              Thread.sleep(20);
            }
            catch(InterruptedException ex) {
              // Do nothing
            }

            update();
            getCollisions();

            objectives();
            repaint();
        }
    }

    public void update() {

    }

    public void objectives() {
      if(frog_saved == objs) {
          // Fin de la partie
      }
    }

    public void getCollisions() {

    }

    public void initialize() {
      Thread t = new Thread(this);
      t.start();
    }

    public static void main(String args[]) {

    }
}
