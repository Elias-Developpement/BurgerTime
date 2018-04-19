import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel implements Runnable {

    private boolean active;

    private Frog player;
    private MapManager map;

    private int objs;
    private int frog_saved;
    /**
     * Creates new form Game
     */
    public Game() {
      player = new Frog();
      map = new MapManager();

      active = false;
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
            try {
              Thread.sleep(20);
            }
            catch(InterruptedException ex) {
              // Do nothing
            }

            update();
            getCollisions();

            objectives();
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

    public void paintComponent(Graphics g) {
      //Graphics2D g2d = (Graphics2D) g;
      //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      for(int x = 0 ; x < 21 ; x++) {
          for(int y = 0 ; y < 16 ; y++) {
              g.drawImage(map.getTileset(),
                          x * 32,
                          y * 32,
                          x * 32 + 32,
                          y * 32 + 32,
                          (int)(map.getTileIndex(x, y) % 8) * 32,
                          (int)(map.getTileIndex(x, y) / 8) * 32,
                          (int)(map.getTileIndex(x, y) % 8) * 32 + 32,
                          (int)(map.getTileIndex(x, y) / 8) * 32 + 32,
                          this);
          }
      }

      /*switch(player.getCurrentSprite()) {
        case 0:
          g.drawImage(player.getSprite(),
                      player.getX(),
                      player.getY(),
                      player.getX() + 32,
                      player.getY() + 32,
                      player.UP * 32,
                      player.UP * 32,
                      player.UP * 32 + 32,
                      player.UP * 32 + 32,
                      this);
          break;
        case 1:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.DOWN * 32,
                    player.DOWN * 32,
                    player.DOWN * 32 + 32,
                    player.DOWN * 32 + 32,
                    this);
          break;
        case 2:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.LEFT * 32,
                    player.LEFT * 32,
                    player.LEFT * 32 + 32,
                    player.LEFT * 32 + 32,
                    this);
          break;
        case 3:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.RIGHT * 32,
                    player.RIGHT * 32,
                    player.RIGHT * 32 + 32,
                    player.RIGHT * 32 + 32,
                    this);
          break;
      }*/
    }
}
