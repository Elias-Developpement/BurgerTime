import java.awt.*;

public class Car extends Object {
    private int width;
    private int height;
    private int speed;
    private Image sprite;

    public Car(String filename) {
      ImageManager im = new ImageManager(filename);
      this.sprite = im.getSprite();
      this.width = im.getWidth();
      this.height = im.getHeight();
      this.speed = 4;
    }

    public void setSpeed(int speed) {
      this.speed = speed;
    }

    public int getSpeed() {
      return this.speed;
    }

    public void setCar(Image car) {
      this.sprite = car;
    }

    public Image getSprite() {
      return this.sprite;
    }
}
