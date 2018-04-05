import java.awt.*;

/*
Frog position
HAUT : 0
BAS : 1
GAUCHE : 2
DROITE 3
*/

public class Frog extends Object {
    private Image[] spritesheet = new Image[4];
    private Image currentSprite;

    public Frog(int x, int y) {
      setX(x);
      setY(y);
    }

    public void initFrog(Image[] frog) {
      this.spritesheet = frog;
      currentSprite = spritesheet[0];
    }

    public void updateFrog(int direction) {
      this.currentSprite = this.spritesheet[direction];
    }

    public void setPosition(int x, int y) {
      setX(x);
      setY(y);
    }

    public void draw(Graphics g) {
      g.drawImage(currentSprite, getX(), getY(), null);
    }
}
