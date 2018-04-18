import java.awt.*;

public class MapManager {
    public final int MAP_WIDTH = 800;
    public final int MAP_HEIGHT = 600;
    public final int MAP_LAYERS = 2;

    private int[][][] map;
    private Image tileset;

    public MapManager() {
        map = new int[MAP_WIDTH / 32][MAP_HEIGHT / 32][MAP_LAYERS];
        initTileset();
        initMap();
    }

    public void initMap() {
        for(int i = 0 ; i < MAP_LAYERS ; i += 32) {
            for(int x = 0 ; x < MAP_WIDTH ; x += 32) {
                for(int y = 0 ; y < MAP_HEIGHT ; y++) {
                    map[x][y][i] = 0;
                }
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

    public int getTileIndex(int x, int y, int layer) {
      return map[x][y][layer];
    }
}
