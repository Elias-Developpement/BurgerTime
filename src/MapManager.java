import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapManager {
    public static final int MAP_WIDTH = 672;
    public static final int MAP_HEIGHT = 480;
    public static final int MAP_LAYERS = 2;
    private static final String SPACE_SEPARATOR = " ";

    private int[][] map;
    private Image tileset;

    public MapManager() {
      map = new int[MAP_WIDTH][MAP_HEIGHT];
      initTileset();
      initMap();
    }

    public void initMap() {
      FileInputStream f = new FileInputStream(new File("map.txt"));
      for(int x = 0 ; x < MAP_WIDTH / 32 ; x++) {
          for(int y = 0 ; y < MAP_HEIGHT / 32 ; y++) {
              map[x][y] =
          }
      }
    }

    public void initTileset() {
      ImageManager img = new ImageManager("tileset", "tileset.png");
      tileset = img.getSprite();
    }

    public Image getTileset() {
      return this.tileset;
    }

    public int getTileIndex(int x, int y) {
      return map[x][y];
    }
}
