package race.level;

import javafx.scene.canvas.GraphicsContext;
import race.Assets;
import race.Game;

public class Map {

    private int width, height;
    private MapTile[][] map;

    public Map(int width, int height) {
        this(width, height, new MapTile[height][width]);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                setTile(y, x, MapTile.DEFAULT);
    }

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

    public void render(GraphicsContext ctx) {
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                getTile(row, col).getTile().render(ctx, Assets.TILESHEET, col * Game.GAME_SCALE, row * Game.GAME_SCALE,
                        Game.GAME_SCALE, Game.GAME_SCALE);
            }
        }
    }

}
