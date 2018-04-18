import java.awt.*;
import java.applet.Applet;

public class Game extends Applet implements Runnable {

    private boolean active;

    private Frog player;
    private MapManager map;

    private int objs;
    private int frog_saved;
    private Graphics screen;
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

    /*public int keyDown(Event e, int key) {
      // Check key pressed and replace the frog + change his current sprite
      switch(key) {
        case Event.UP:
          player.updateFrog(0);
          player.setPosition(player.getX(), player.getY() - 32);
          break;
        case Event.DOWN:
          player.updateFrog(1);
          player.setPosition(player.getX(), player.getY() + 32);
          break;
        case Event.LEFT:
          player.updateFrog(2);
          player.setPosition(player.getX() - 32, player.getY());
          break;
        case Event.RIGHT:
          player.updateFrog(3);
          player.setPosition(player.getX() + 32, player.getY());
          break;
      }

      return 1;
    }*/

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
      paint(screen);
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

    public void paint(Graphics g) {
      for(int i = 0 ; i < map.MAP_LAYERS ; i += 32) {
        for(int x = 0 ; x < map.MAP_WIDTH ; x += 32) {
          for(int y = 0 ; y < map.MAP_HEIGHT ; y++) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(map.getTileset(), x, y, x + 32, y + 32, map.getTileIndex(x, y, i) % 8, map.getTileIndex(x, y, i) / 8, map.getTileIndex(x, y, i) % 8 + 32, map.getTileIndex(x, y, i) / 8 + 32, this);
          }
        }
      }
    }
}
