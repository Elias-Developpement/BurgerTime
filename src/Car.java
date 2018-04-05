import java.awt.*;

public class Car extends Object {
    public int speed;
    public Image sprite;

    public Car() {
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

    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), null);
    }
}
