import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class ImageManager {
  private BufferedImage sprite;

  public ImageManager(String filename) {
    try {
      sprite = ImageIO.read(new File("../images/" + filename));
    }
    catch(IOException e) {

    }
  }

  public void setSprite(String filename) {
    try {
      sprite = ImageIO.read(new File("../images/" + filename));
    }
    catch(IOException e) {

    }
  }

  public void setSprite(BufferedImage img) {
    sprite = img;
  }

  public int getWidth() {
    return sprite.getWidth();
  }

  public int getHeight() {
    return sprite.getHeight();
  }

  public Image getSprite() {
    return (Image)sprite;
  }
}
