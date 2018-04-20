import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JFrame;

// Return collisions
/*
* 0 : Nothing
* 1 : Section speed 1 - gauche
* 2 : Section speed 2 - gauche
* 3 : Section speed 3 - gauche
* 4 : Section speed 1 - droite
* 5 : Section speed 2 - droite
* 6 : Section speed 3 - droite
* 7 : Collision
*/

public class Game extends JPanel implements KeyListener, Runnable {
    private static final long MAX = 3000;
    private static final long MIN = 2000;
    private static final long TIME = 40000;
    private boolean gameloop;

    private Frog player;
    private MapManager map;
    List<Car> carsLeft = new ArrayList<Car>();
    List<Car> carsRight = new ArrayList<Car>();
    List<Section> sectionLeft = new ArrayList<Section>();
    List<Section> sectionRight = new ArrayList<Section>();
    private Thread thread;

    private Time time;
    private Time liveTime;

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

      addSection(3, 1, 0, 2);
      addSection(2, 0, 1, 1);
      addSection(4, 1, 2, 3);
      addSection(2, 1, 3, 1);
      addSection(3, 0, 4, 2);

      time = new Time();
      liveTime = new Time();

      gameloop = true;
      frog_saved = 0;
    }

    public synchronized void start() {
      thread = new Thread(this, "Main");
      thread.start();
    }

    public void run() {
        while(gameloop) {
            // Update screen
            try {
              Thread.sleep(20);
            }
            catch(InterruptedException e) {
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

            rand = (long)(Math.random() * (MAX - MIN) + MIN);
            if(time.getElapasedTime() >= rand) {
              addSection(3, 1, 0, 2);
              addSection(2, 0, 1, 1);
              addSection(4, 1, 2, 3);
              addSection(2, 1, 3, 1);
              addSection(3, 0, 4, 2);

              time.reset();
            }

            if(liveTime.getElapasedTime() >= TIME) {
              player.collision();
              liveTime.reset();
            }

            update();
            updateCars();
            updateSections();
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
      switch(checkCollisions()) {
        case 1:
          player.setX(player.getX() - 1);
          break;
        case 2:
          player.setX(player.getX() - 2);
          break;
        case 3:
          player.setX(player.getX() - 3);
          break;
        case 4:
          player.setX(player.getX() + 1);
          break;
        case 5:
          player.setX(player.getX() + 2);
          break;
        case 6:
          player.setX(player.getX() + 3);
          break;
        case 7:
          player.collision();
          break;
      }

      // Check victory condition
      if(frog_saved >= 6) {
        repaint();
        endGame();
      }

      // Check death
      if(player.getLive() <= 0) {
        repaint();
        endGame();
      }
    }

    public int checkCollisions() {
      int temp = 0;
      for(int i = 0 ; i < carsLeft.size() ; i++) {
        if(carsLeft.get(i).getX() / 32 == player.getX() / 32 && carsLeft.get(i).getY() / 32 == player.getY() / 32) {
           // Collision
           return 7;
         }
      }

      for(int i = 0 ; i < carsRight.size() ; i++) {
        if(carsRight.get(i).getX() / 32 == player.getX() / 32 && carsRight.get(i).getY() / 32 == player.getY() / 32) {
           // Collision
           return 7;
         }
      }

      for(int i = 0 ; i < sectionLeft.size() ; i++) {
         if(map.getTileIndex(player.getX() / 32, player.getY() / 32) == 14) {
           if((player.getX() + 16) > sectionLeft.get(i).getX() &&
               player.getX() < sectionLeft.get(i).getX() + sectionLeft.get(i).getSize() * 32 &&
               player.getY() == sectionLeft.get(i).getY()) {
                 switch(sectionLeft.get(i).getSpeed()) {
                   case 1:
                    return 1;
                   case 2:
                    return 2;
                   case 3:
                    return 3;
                 }
           }
            else {
              temp = 7;
            }
         }
      }

      for(int i = 0 ; i < sectionRight.size() ; i++) {
         if(map.getTileIndex(player.getX() / 32, player.getY() / 32) == 14) {
           if((player.getX() + 16) > sectionRight.get(i).getX() &&
               player.getX() < sectionRight.get(i).getX() + sectionRight.get(i).getSize() * 32 &&
               player.getY() == sectionRight.get(i).getY()) {
              // Collision
              switch(sectionRight.get(i).getSpeed()) {
                case 1:
                 return 4;
                case 2:
                 return 5;
                case 3:
                 return 6;
              }
            }
            else {
              temp = 7;
            }
         }
      }

      if(player.getX() < 0 || player.getX() > map.MAP_WIDTH) {
        return 7;
      }

      switch(map.getTileIndex(player.getX() / 32, player.getY() / 32)) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 17:
          return 7;
      }

      if(map.getTileIndex(player.getX() / 32, player.getY() / 32) == 4) {
        map.setTileIndex(player.getX() / 32, player.getY() / 32, 17);
        frog_saved++;
        player.setX((6 * 32));
        player.setY((13 * 32));
        player.setCurrentSprite(0);
      }

      return temp;
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

    public void updateSections() {
      for(int i = 0 ; i < sectionLeft.size() ; i++) {
        sectionLeft.get(i).setX(sectionLeft.get(i).getX() - sectionLeft.get(i).getSpeed());

        if(sectionLeft.get(i).getX() <= (-32 * sectionLeft.get(i).getSize())) {
          sectionLeft.remove(i);
        }
      }

      for(int i = 0 ; i < sectionRight.size() ; i++) {
        sectionRight.get(i).setX(sectionRight.get(i).getX() + sectionRight.get(i).getSpeed());

        if(sectionRight.get(i).getX() >= map.MAP_WIDTH + 32) {
          sectionRight.remove(i);
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

    public void addSection(int size, int direction, int line, int speed) {
      Section section = new Section("section_" + size + ".png");
      section.setSpeed(speed);
      section.setSize(size);

      switch(direction) {
        case 0:
          // Left
          section.setX(map.MAP_WIDTH + 32);
          section.setY(64 + (line * 32));
          sectionLeft.add(section);
          break;
        case 1:
          // Right
          section.setX(-32 * size);
          section.setY(64 + (line * 32));
          sectionRight.add(section);
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

      for(int i = 0 ; i < sectionLeft.size() ; i++) {
        g.drawImage(sectionLeft.get(i).getSprite(), sectionLeft.get(i).getX(), sectionLeft.get(i).getY(), this);
      }

      for(int i = 0 ; i < sectionRight.size() ; i++) {
        g.drawImage(sectionRight.get(i).getSprite(), sectionRight.get(i).getX(), sectionRight.get(i).getY(), this);
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

      // HUD
      Font arial = new Font("Arial", Font.BOLD, 14);
      Color white = new Color(255, 255, 255);
      g.setFont(arial);
      g.setColor(white);
      g.drawString("Vies : " + player.getLive(), 10, 20);
      g.drawString("Temps restant : " + (40 - ((int)(liveTime.getElapasedTime() / 1000))) + "s", 10, 40);

      if(frog_saved >= 7) {
        Font victory = new Font("Arial", Font.BOLD, 38);
        g.setFont(victory);
        g.drawString("VOUS AVEZ GAGNE !", 10, 100);
      }

      if(player.getLive() <= 0) {
        Font victory = new Font("Arial", Font.BOLD, 38);
        g.setFont(victory);
        g.drawString("VOUS AVEZ PERDU !", 10, 100);
      }
    }

    public static void main(String []args) {
      Window window = new Window();
    }
}
