import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel implements KeyListener, Runnable {
    private boolean gameloop;

    private Frog player;
    private MapManager map;
    private Thread thread;

    private int objs;
    private int frog_saved;
    /**
     * Creates new form Game
     */
    public Game() {
      map = new MapManager();
      player = new Frog();

      gameloop = true;
      objs = 10;
      frog_saved = 0;
    }

    public synchronized void start() {
      thread = new Thread(this, "Display");
      thread.start();
    }

    public void run() {
        while(gameloop) {
            // Update screen
            try {
              Thread.sleep(20);
            }
            catch(InterruptedException ex) {
              // Do nothing
            }

            update();
            repaint();
        }
    }

    public synchronized void stop() {
      try {
        thread.join();
        System.exit(0);
      }
      catch(InterruptedException e) {
        e.printStackTrace();
      }
    }

    public void update() {

    }

    public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
          player.setCurrentSprite(0);
          if(player.getY() - 32 >= 0) {
            player.setPosition(player.getX(), player.getY() - 32);
          }
          break;
        case KeyEvent.VK_DOWN:
          player.setCurrentSprite(1);
          if(player.getY() + 32 < map.MAP_HEIGHT - 32) {
            player.setPosition(player.getX(), player.getY() + 32);
          }
          break;
        case KeyEvent.VK_LEFT:
          player.setCurrentSprite(2);
          if(player.getX() - 32 >= 0) {
            player.setPosition(player.getX() - 32, player.getY());
          }
          break;
        case KeyEvent.VK_RIGHT:
          player.setCurrentSprite(3);
          if(player.getX() + 32 < map.MAP_WIDTH) {
            player.setPosition(player.getX() + 32, player.getY());
          }
          break;
      }
    }

    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
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

     switch(player.getCurrentSprite()) {
        case 0:
          g.drawImage(player.getSprite(),
                      player.getX(),
                      player.getY(),
                      player.getX() + 32,
                      player.getY() + 32,
                      player.UP * 32,
                      0 * 32,
                      player.UP * 32 + 32,
                      0 * 32 + 32,
                      this);
          break;
        case 1:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.DOWN * 32,
                    0 * 32,
                    player.DOWN * 32 + 32,
                    0 * 32 + 32,
                    this);
          break;
        case 2:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.LEFT * 32,
                    0 * 32,
                    player.LEFT * 32 + 32,
                    0 * 32 + 32,
                    this);
          break;
        case 3:
        g.drawImage(player.getSprite(),
                    player.getX(),
                    player.getY(),
                    player.getX() + 32,
                    player.getY() + 32,
                    player.RIGHT * 32,
                    0 * 32,
                    player.RIGHT * 32 + 32,
                    0 * 32 + 32,
                    this);
          break;
      }
    }

    public static void main(String []args) {
      Window window = new Window();
    }
}
