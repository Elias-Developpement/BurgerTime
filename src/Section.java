import java.awt.*;

public class Section extends Object {
    private int width;
    private int height;
    private int speed;
    private int size;
    private Image sprite;

    public Section(String filename) {
      ImageManager im = new ImageManager(filename);
      this.sprite = im.getSprite();
      this.width = im.getWidth();
      this.height = im.getHeight();
      this.speed = 1;
    }

    public void setSpeed(int speed) {
      this.speed = speed;
    }

    public int getSpeed() {
      return this.speed;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public int getSize() {
      return size;
    }

    public void setSprite(Image section) {
      this.sprite = section;
    }

    public Image getSprite() {
      return this.sprite;
    }
}
