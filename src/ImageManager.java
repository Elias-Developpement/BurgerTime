import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class ImageManager {
  private Image sprite;

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

  public void setSprite(Image img) {
    sprite = img;
  }

  public Image getSprite() {
    return sprite;
  }
}
