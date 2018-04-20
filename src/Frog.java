import java.awt.*;

public class Frog extends Object {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private Image sprite;
    private int currentSprite;

    public Frog(int x, int y) {
      setX(x);
      setY(y);

      // Set the frog position to UP
      currentSprite = UP;

      ImageManager im = new ImageManager("frog.png");
      sprite = im.getSprite();

      System.out.println("Grenouille creee");
    }

    public Frog() {
      setX((6 * 32));
      setY((13 * 32));
      // Set the frog position to UP
      currentSprite = UP;

      ImageManager im = new ImageManager("frog.png");
      sprite = im.getSprite();
    }

    public void setPosition(int x, int y) {
      setX(x);
      setY(y);
    }

    public void setCurrentSprite(int direction) {
      currentSprite = direction;
    }

    public int getCurrentSprite() {
      return currentSprite;
    }

    public Image getSprite() {
      return sprite;
    }
}
