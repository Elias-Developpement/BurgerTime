import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel implements KeyListener, Runnable {
    private static final long MAX = 3000;
    private static final long MIN = 2000;
    private boolean gameloop;

    private Frog player;
    private MapManager map;
    List<Car> carsLeft = new ArrayList<Car>();
    List<Car> carsRight = new ArrayList<Car>();
    private Thread thread;

    private Time time;

    private int objs;
    private int frog_saved;
    /**
     * Creates new form Game
     */
    public Game() {
      map = new MapManager();
      player = new Frog();
      addCar("car_left.png", 0, 0, 1);
      addCar("car_right.png", 1, 1, 2);
      addCar("car_left.png", 0, 2, 3);
      addCar("car_right.png", 1, 3, 2);
      addCar("car_left.png", 0, 4, 3);

      time = new Time();

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

            long rand = (long)(Math.random() * (MAX - MIN) + MIN);
            if(time.getElapasedTime() >= rand) {
              addCar("car_left.png", 0, 0, 1);
              addCar("car_right.png", 1, 1, 2);
              addCar("car_left.png", 0, 2, 3);
              addCar("car_right.png", 1, 3, 2);
              addCar("car_left.png", 0, 4, 3);

              time.reset();
            }

            update();
            updateCars();
            checkCollisions();
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

    public void checkCollisions() {
      for(int i = 0 ; i < carsLeft.size() ; i++) {
        if(carsLeft.get(i).getX() / 32 == player.getX() / 32 && carsLeft.get(i).getY() / 32 == player.getY() / 32) {
             // Collision
             System.out.println("Collision");
             player.collision();
           }
      }

      for(int i = 0 ; i < carsRight.size() ; i++) {
        if(carsRight.get(i).getX() / 32 == player.getX() / 32 && carsRight.get(i).getY() / 32 == player.getY() / 32) {
             // Collision
             System.out.println("Collision");
             player.collision();
           }
      }

      if(player.getLive() <= 0) {
        endGame();
      }
    }

    public void endGame() {
      gameloop = false;
      stop();
    }

    public void updateCars() {
      for(int i = 0 ; i < carsLeft.size() ; i++) {
        carsLeft.get(i).setX(carsLeft.get(i).getX() - carsLeft.get(i).getSpeed());

        if(carsLeft.get(i).getX() <= -32) {
          carsLeft.remove(i);
        }
      }

      for(int i = 0 ; i < carsRight.size() ; i++) {
        carsRight.get(i).setX(carsRight.get(i).getX() + carsRight.get(i).getSpeed());

        if(carsRight.get(i).getX() >= map.MAP_WIDTH + 32) {
          carsRight.remove(i);
        }
      }
    }

    public void addCar(String filename, int direction, int line, int speed) {
      Car car = new Car(filename);
      car.setSpeed(speed);

      switch(direction) {
        case 0:
          // Left
          car.setX(map.MAP_WIDTH + 32);
          car.setY(256 + (line * 32));
          carsLeft.add(car);
          break;
        case 1:
          // Right
          car.setX(-32);
          car.setY(256 + (line * 32));
          carsRight.add(car);
          break;
      }
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

      for(int i = 0 ; i < carsLeft.size() ; i++) {
        g.drawImage(carsLeft.get(i).getSprite(), carsLeft.get(i).getX(), carsLeft.get(i).getY(), this);
      }

      for(int i = 0 ; i < carsRight.size() ; i++) {
        g.drawImage(carsRight.get(i).getSprite(), carsRight.get(i).getX(), carsRight.get(i).getY(), this);
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
