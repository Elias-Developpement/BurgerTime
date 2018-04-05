import java.awt.*;

public class ImageManager {
  private Image sprite;

  public ImageManager(String type, String filename) {
    sprite = getImage(getCodeBase(), "images/" + type + "/" + filename);
  }

  public void setSprite(String type, String filename) {
    sprite = getImage(getCodeBase(), "images/" + type + "/" + filename);
  }

  public void setSprite(Image img) {
    sprite = img;
  }

  public Image getSprite() {
    return sprite;
  }
}
