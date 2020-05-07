package race.level;

import race.Assets;
import race.Renderer;

/**
 * A map consists of a 2D grid of MapTiles which describes a level's terrain.
 * Locations are specified using integer map coordinates, which correspond
 * exactly to single MapTiles in the grid.
 */
public class Map {
    private int width, height;
    private MapTile[][] map;

    public Map(int width, int height, MapTile[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWithinMap(int row, int col) {
        return row >= 0 && col >= 0 && row < height && col < width;
    }

    public MapTile getTile(int row, int col) {
        return map[row][col];
    }

    public void setTile(int row, int col, MapTile t) {
        map[row][col] = t;
    }

    public void render(Renderer renderer) {
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                getTile(row, col).getTile().render(renderer, Assets.TILESHEET, col, row);
            }
        }
    }
}
