import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MapManager {
    public static final int MAP_WIDTH = 13 * 32;
    public static final int MAP_HEIGHT = 15 * 32;
    public static final int MAP_LAYERS = 2;

    private int[][] map;
    private Image tileset;

    public MapManager() {
      map = new int[MAP_WIDTH][MAP_HEIGHT];
      initTileset();
      initMap();

      System.out.println("Map creee");
    }

    public void initMap() {
      FileInputStream input = null;
      InputStreamReader stream = null;
      LineNumberReader reader = null;

      List<Integer> list = new ArrayList<Integer>();

      try {
        input = new FileInputStream(new File("map.txt"));
        stream = new InputStreamReader(input);
      	reader = new LineNumberReader(stream);
        String line = null;

        while((line = reader.readLine()) != null) {
          String[] tempValues = line.split(" ");
          for(String s : tempValues) {
            list.add(Integer.parseInt(s));
          }
        }

        reader.close();
        stream.close();
        input.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }

      int i = 0;
      int j = 0;

      for(int k = 0 ; k < list.size() ; k++) {
        map[i][j] = list.get(k);
        i++;

        if(i >= (MAP_WIDTH / 32) && j <= (MAP_HEIGHT / 32)) {
          i = 0;
          j++;
        }
      }
    }

    public void debug() {
      for(int x = 0 ; x < MAP_WIDTH / 32 ; x++) {
          for(int y = 0 ; y < MAP_HEIGHT / 32 ; y++) {
              System.out.print(map[x][y] + " ");
          }
          System.out.println("");
      }
    }

    public void initTileset() {
      ImageManager img = new ImageManager("tileset.png");
      tileset = img.getSprite();
    }

    public Image getTileset() {
      return this.tileset;
    }

    public int getTileIndex(int x, int y) {
      return map[x][y];
    }

    public void setTileIndex(int x, int y, int value) {
      map[x][y] = value;
    }
}
