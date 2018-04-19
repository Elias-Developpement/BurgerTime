import java.awt.*;

public class Frog extends Object {
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private Image sprite;
    private int currentSprite;

    public Frog(int x, int y) {
      setX(x);
      setY(y);

      // Set the frog position to UP
      currentSprite = UP;

      ImageManager im = new ImageManager("frog.png");
      sprite = im.getSprite();
    }

    public Frog() {
      setX((12 * 32));
      setY((6 * 32));
      // Set the frog position to UP
      currentSprite = UP;
    }

    public void setPosition(int x, int y) {
      setX(x);
      setY(y);
    }

    public int getCurrentSprite() {
      return currentSprite;
    }

    public Image getSprite() {
      return sprite;
    }

    /*public void draw(Graphics g) {
      g.drawImage(currentSprite, getX(), getY(), null);
    }*/
}
