import java.awt.*;

public class MapManager {
    private final int MAP_WIDTH = 800;
    private final int MAP_HEIGHT = 600;
    private final int MAP_LAYERS = 2;

    private Image[][][] map;
    private Image[] tileset;

    public MapManager() {
        map = new Image[MAP_WIDTH][MAP_HEIGHT][MAP_LAYERS];
        initTileset();
        initMap();
    }

    public void initMap() {
        for(int i = 0 ; i < MAP_LAYERS ; i++) {
            for(int x = 0 ; x < MAP_WIDTH ; x++) {
                for(int y = 0 ; y < MAP_HEIGHT ; y++) {
                    map[x][y][i] = tileset[0];
                }
            }
        }
    }

    public void initTileset() {
      ImageManager img = new ImageManager("tileset", "tileset.png");
      tileset = img.getSprite();
    }

    public display() {
      for(int i = 0 ; i < MAP_LAYERS ; i++) {
        for(int x = 0 ; x < MAP_WIDTH ; x++) {
          for(int y = 0 ; y < MAP_HEIGHT ; y++) {
            
          }
        }
      }
    }
}
